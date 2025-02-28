package cn.edu.jlu.service;

import cn.edu.jlu.client.DishClient;
import cn.edu.jlu.client.OrderClient;
import cn.edu.jlu.client.ShopClient;
import cn.edu.jlu.mapper.CartMapper;
import cn.edu.jlu.po.*;
import jakarta.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class CartServiceImpl implements CartService{
    @Resource
    private CartMapper cartMapper;

    @Resource
    private OrderClient orderClient;

    @Resource
    private DishClient dishClient;

    @Resource
    private ShopClient shopClient;

    @Override
    public Cart addCart(Cart cart) {
        //当前购物车中有该菜品，更新数量
        if (cartMapper.selectByUserIdShopIdDishId(cart.getUser_id(), cart.getShop_id(), cart.getDish_id())!= null) {
            Cart oldCart = cartMapper.selectByUserIdShopIdDishId(cart.getUser_id(), cart.getShop_id(), cart.getDish_id());
            oldCart.setQuantity(oldCart.getQuantity() + cart.getQuantity());
            oldCart.setPrice(oldCart.getPrice().add(cart.getPrice()));
            cartMapper.updateById(oldCart);
            return oldCart;
        }
        Cart newCart = new Cart();
        newCart.setUser_id(cart.getUser_id());
        newCart.setShop_id(cart.getShop_id());
        newCart.setDish_id(cart.getDish_id());
        newCart.setQuantity(cart.getQuantity());
        newCart.setPrice(cart.getPrice());
        cartMapper.insert(newCart);
        return newCart;
//        return cart;
    }

    @Override
    public Cart updateCart(Cart cart) {
        if(cart.getQuantity() != 0 && cart.getQuantity() != null){
            BigDecimal singlePrice = dishClient.findProductById(cart.getDish_id()).getData().getPrice();
            cart.setPrice(singlePrice.multiply(new BigDecimal(cart.getQuantity())));

        }
        cartMapper.updateById(cart);
        cart = cartMapper.selectById(cart.getId());
        return cart;
    }

    @Override
    public void deleteCart(Cart cart) {
        try{
            int affectedRows = cartMapper.deleteById(cart.getId());
            if (affectedRows == 0) {
                throw new ServiceException("要删除的订单不存在，ID：" + cart.getId());
            }
        }catch(DataAccessException e){
            throw new ServiceException("订单删除失败，请重试");
        }
    }

    @Override
    public List<Cart> getCartByUserId(Integer user_id) {
        List<Cart> cartList = cartMapper.findCartByUserId(user_id);
        return cartList;
    }

    @Override
    public List<Order> placeOrderByUserId(List<Cart> carts, Integer addressId) {
        Integer user_id = carts.get(0).getUser_id();
        List<Integer> shop_ids = carts.stream().map(Cart::getShop_id).toList();
        List<Order> orders = new ArrayList<>();
        for(Integer shop_id : shop_ids){
            List<Cart> cartForShop = carts.stream()
                    .filter(cart -> cart.getShop_id().equals(shop_id))
                    .toList();
            BigDecimal total_price = cartForShop.stream()
                    .map(Cart::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            Order order = new Order();
            Random random = new Random();
            order.setId(random.nextInt(100000));
            order.setUser_id(user_id);
            order.setShop_id(shop_id);
            order.setAddress_id(addressId);
            order.setTotal_price(total_price);
            order.setStatus(0);
            order.setRemark("");
            orders.add(order);
            orderClient.createOrder(order);
            for(Integer dish_id : cartForShop.stream().map(Cart::getDish_id).toList()){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder_id(order.getId());
                orderItem.setDish_name(dishClient.findProductById(dish_id).getData().getName());
                orderItem.setDish_id(dish_id);
                Integer quantity = cartForShop.stream()
                        .filter(cart -> cart.getDish_id().equals(dish_id))
                        .map(Cart::getQuantity)
                        .findFirst()
                        .orElse(0);
                BigDecimal price = cartForShop.stream()
                        .filter(cart -> cart.getDish_id().equals(dish_id))
                        .map(Cart::getPrice)
                        .findFirst()
                        .orElse(BigDecimal.ZERO);
                orderItem.setQuantity(quantity);
                orderItem.setPrice(price);
                orderClient.createOrderItem(orderItem);
            }
        }
        return orders;
    }
    @Override
    public Shop getShopById(Integer shop_id){
        return shopClient.info(shop_id).getData();
    }

    @Override
    public Product getProductById(Integer dish_id){
        return dishClient.findProductById(dish_id).getData();
    }

    @Override
    public CartItem getCartItemByCartId(Integer cartId){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cartMapper.selectById(cartId));
        cartItem.setShop(shopClient.info(cartItem.getCart().getShop_id()).getData());
        cartItem.setProduct(dishClient.findProductById(cartItem.getCart().getDish_id()).getData());
        return cartItem;
    }

    @Override
    public List<Order> checkout(Integer userId){
        List<Order> orders = orderClient.findOrderByUserId(userId).getData();
        List<Order> checkedOrders = new ArrayList<>();
        for(Order order : orders){
            if(order.getStatus().equals(0))
                order.setStatus(1);
            orderClient.update(order);
            checkedOrders.add(order);
        }
        return checkedOrders;
    }
}
