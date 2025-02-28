package cn.edu.jlu.client;


import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-user")
public interface UserClient {
    @RequestMapping("/user/info/{id}")
    public Result<User> findUserById(@PathVariable("id") Integer id);
    @PutMapping("/user/update")
    public Result<User> update(@RequestBody User user);
    @RequestMapping("/user/history/{userId}")
    public Result<List<Order>> findOrderByUserId(@PathVariable("userId") Integer userId);
    @RequestMapping("/user/list")
    public List<User> findAllUser();
}
