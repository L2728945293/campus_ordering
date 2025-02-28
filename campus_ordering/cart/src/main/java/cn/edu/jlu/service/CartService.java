package cn.edu.jlu.service;

import cn.edu.jlu.po.*;

import java.util.List;

public interface CartService {
    Cart addCart(Cart cart);
    Cart updateCart(Cart cart);
    void deleteCart(Cart cart);
    List<Cart> getCartByUserId(Integer user_id);
    Shop getShopById(Integer shop_id);
    Product getProductById(Integer dish_id);
    List<Order> placeOrderByUserId(List<Cart> carts, Integer addressId);
    CartItem getCartItemByCartId(Integer cartId);
    List<Order> checkout(Integer userId);
}
