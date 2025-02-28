package cn.edu.jlu.client;


import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.OrderItem;
import cn.edu.jlu.po.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-order")
public interface OrderClient {
    @PostMapping("/order/create")
    public Result<Order> createOrder(@RequestBody Order order);
    @PostMapping("/order/item/create")
    public Result<OrderItem> createOrderItem(@RequestBody OrderItem orderItem);

    @RequestMapping("/order/user/{userId}")
    public Result<List<Order>> findOrderByUserId(@PathVariable("userId") Integer userId);

    @PutMapping("/order/update")
    public Result<Order> update(@RequestBody Order order);
}
