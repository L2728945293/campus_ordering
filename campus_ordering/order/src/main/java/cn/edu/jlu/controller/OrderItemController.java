package cn.edu.jlu.controller;


import cn.edu.jlu.po.NewOrderItemBody;
import cn.edu.jlu.po.OrderItem;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.service.OrderItemService;
import jakarta.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order/item")
public class OrderItemController {
    @Resource
    private OrderItemService orderItemService;
    @RequestMapping("/{id}")
    public Result<OrderItem> findOrderItemById(@PathVariable("id") Integer id) {
        OrderItem orderItem = orderItemService.findOrderItemById(id);
        return Result.success(orderItem);
    }

    @RequestMapping("/order/{orderId}")
    public Result<List<OrderItem>> findOrderItemByOrderId(@PathVariable("orderId") Integer orderId) {
        List<OrderItem> orderItem = orderItemService.findOrderItemByOrderId(orderId);
        return Result.success(orderItem);
    }

    @PostMapping("/create")
    public Result<OrderItem> createOrderItem(@RequestBody OrderItem orderItem) {
//        OrderItem orderItem = orderItemService.create();
        return Result.success(orderItemService.create(orderItem));
    }

    @PutMapping("/update")
    public Result<OrderItem> update(@RequestBody OrderItem orderItem) {
        orderItem = orderItemService.update(orderItem);
        return Result.success(orderItem);
    }

    @DeleteMapping("/delete")
    public Result<String> delete(@RequestBody OrderItem orderItem) {
        try{
            orderItemService.delete(orderItem);
            return Result.success("删除成功");
        }
        catch(ServiceException e){
            return Result.error(400, e.getMessage());
        }
    }
}
