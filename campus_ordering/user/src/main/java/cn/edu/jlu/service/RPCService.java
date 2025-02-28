package cn.edu.jlu.service;

import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Result;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@FeignClient("service-order")
public interface RPCService {
//    @RequestMapping("/order/user/{userId}")
//    Result<List<Order>> findOrderByUserId(@PathVariable("userId") Integer userId);
}
