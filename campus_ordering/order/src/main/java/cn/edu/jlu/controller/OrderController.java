package cn.edu.jlu.controller;


import cn.edu.jlu.po.*;
import cn.edu.jlu.service.OrderService;
import jakarta.annotation.Resource;
import org.aspectj.weaver.ast.Or;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/{id}")
    public Result<Order> findOrderById(@PathVariable("id") Integer id) {
        Order order = orderService.findOrderById(id);
        return Result.success(order);
    }
    @RequestMapping("/user/{userId}")
    public Result<List<Order>> findOrderByUserId(@PathVariable("userId") Integer userId) {
        List<Order> orders = orderService.findOrderByUserId(userId);
        orders.sort((o1, o2) -> o2.getCreate_time().compareTo(o1.getCreate_time()));
        return Result.success(orders);
    }
    @RequestMapping("/shop/{shopId}")
    public Result<List<Order>> findOrderByShopId(@PathVariable("shopId") Integer shopId) {
        List<Order> orders = orderService.findOrderByShopId(shopId);
        return Result.success(orders);
    }
    @PostMapping("/create")
    public Result<Order> createOrder(@RequestBody Order order) {
        return Result.success(orderService.create(order));
    }
    @PutMapping("/update")
    public Result<Order> update(@RequestBody Order order) {
        Order updatedOrder = orderService.update(order);
        return Result.success(updatedOrder);
    }
    @DeleteMapping("/order/delete/{id}")
    public Result deleteOrderById(@PathVariable("id") Integer id){
        try{
            orderService.deleteById(id);
            return Result.success("删除成功");
        }
        catch(ServiceException e){
            return Result.error(400, e.getMessage());
        }
    }

    @RequestMapping("/shop/turnover")
    public Result<BigDecimal> getShopTurnover(@RequestBody TurnOverBody turnOverBody) {
        BigDecimal turnover = orderService.getShopTurnover(turnOverBody.getShopId(), turnOverBody.getStartTime(), turnOverBody.getEndTime());
        return Result.success(turnover);
    }

    @RequestMapping("/order/address/{orderId}")
    public Result<Address> getAddressByOrderId(@PathVariable("orderId") Integer orderId) {
        Order order = orderService.findOrderById(orderId);

        Address address = orderService.getAddressByOrderId(order.getAddress_id());
        return Result.success(address);
    }


}
