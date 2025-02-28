package cn.edu.jlu.shop.client;

//import cn.edu.jlu.shop.po.common.Order;
import cn.edu.jlu.po.NewOrderBody;
import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.TurnOverBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.awt.datatransfer.Clipboard;
import java.math.BigDecimal;
import java.util.List;

@FeignClient("service-order")
public interface OrderClient {
    @RequestMapping("/order/create")
    public Result<Order> saveOrder(@RequestBody NewOrderBody newOrderBody);
    @GetMapping("/order/{id}")
    public Result<Order> findOrderById(@PathVariable("id") Integer id);
    @DeleteMapping("/order/order/delete/{id}")
    public Result deleteOrderById(@PathVariable("id") Integer id);
    @PutMapping("/order/update")
    public Result<Order> updateOrder(@RequestBody Order order);
    @RequestMapping("/order/shop/turnover")
    public Result<BigDecimal> getShopTurnover(@RequestBody TurnOverBody turnOverBody);
    @RequestMapping("/order/shop/{shopId}")
    public Result<List<Order>> findOrderByShopId(@PathVariable("shopId") Integer shopId);
}
