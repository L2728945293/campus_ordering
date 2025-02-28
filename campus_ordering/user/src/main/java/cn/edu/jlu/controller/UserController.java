package cn.edu.jlu.controller;

import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.User;
import cn.edu.jlu.service.RPCService;
import cn.edu.jlu.service.UserService;
import common.LoginBody;
import common.RegisterBody;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// 示例路径，需根据实际包结构调整

@CrossOrigin
@RestController
@RequestMapping("/user")

public class UserController {
    @Resource
    UserService service;


    @RequestMapping("/info/{id}")
    public Result<User> findUserById(@PathVariable("id") Integer id){
        Result<User> R = new Result<User>();
        R.setData(service.findUserById(id));
        return Result.success(R.getData());
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody LoginBody loginBody){
        Result<User> R = new Result<User>();
        R.setData(service.login(loginBody));
        return Result.success(R.getData());
    }

    @PostMapping("/register")
    public Result<User> register(@RequestBody RegisterBody registerBody){
        Result<User> R = new Result<User>();
        R.setData(service.register(registerBody));
        return Result.success(R.getData());
    }

    @PutMapping("/update")
    public Result<User> update(@RequestBody User user){
        Result<User> R = new Result<User>();
        R.setData(service.update(user));
        return Result.success(R.getData());
    }


//    @RequestMapping("/history/{userId}")
//    public Result<List<Order>> findOrderByUserId(@PathVariable("userId") Integer userId){
//        return rpcService.findOrderByUserId(userId);
//    }

    @RequestMapping("/list")
    public List<User> findAllUser(){
        return service.findAllUser();
    }
}