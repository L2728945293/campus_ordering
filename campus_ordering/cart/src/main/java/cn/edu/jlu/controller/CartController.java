package cn.edu.jlu.controller;


import cn.edu.jlu.client.ShopClient;
import cn.edu.jlu.po.*;
import cn.edu.jlu.service.CartService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Resource
    private CartService cartService;



    @PostMapping("/add")
    public Result<Cart> addCart(@RequestBody Cart cart) {
        log.info("Add cart: " + cart);
        return Result.success(cartService.addCart(cart));
    }

    @PutMapping("/update")
    public Result<Cart> updateCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.updateCart(cart);
        return Result.success(updatedCart);
    }

    @DeleteMapping("/delete")
    public Result<String> deleteCart(@RequestBody Cart cart) {
        try{
            cartService.deleteCart(cart);
            return Result.success("删除成功");
        }
        catch(ServiceException e){
            return Result.error(400, e.getMessage());
        }
    }

    @RequestMapping("/{userId}")
    public Result<List<CartItem>> getCartByUserId(@PathVariable("userId") Integer userId) {
        List<Cart> carts = cartService.getCartByUserId(userId);
        List<CartItem> cartItems = new ArrayList<>();
        for(Cart cart : carts){
            CartItem cartItem = cartService.getCartItemByCartId(cart.getId());
            cartItems.add(cartItem);
        }
        return Result.success(cartItems);
    }

    @PostMapping("/place")
    public Result<List<Order>> placeOrderByUserId(@RequestBody Address address){
        List<Cart> carts = cartService.getCartByUserId(address.getUser_id());
        List<Order> orders = cartService.placeOrderByUserId(carts, address.getId());
        for(Cart cart : carts)
            cartService.deleteCart(cart);
        log.info("Place order success");
        return Result.success(orders);
    }

    @GetMapping("/checkout/{userId}")
    public Result<List<Order>> checkout(@PathVariable("userId") Integer userId){
        List<Order> orders = cartService.checkout(userId);
        return Result.success(orders);
    }

}
