package cn.edu.jlu.AdminController;

import cn.edu.jlu.AdminService.AdminService;
import cn.edu.jlu.client.ShopClient;
import cn.edu.jlu.client.UserClient;
import cn.edu.jlu.po.Admin;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.Shop;
import cn.edu.jlu.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ShopClient shopClient;
    @PostMapping("/login")
    public Result<Admin> login(@RequestBody Admin admin){
        return Result.success(adminService.login(admin));
    }

    @PostMapping("/register")
    public Result<Admin> register(@RequestBody Admin admin){
        return Result.success(adminService.register(admin));
    }
    @RequestMapping("/user")
    public Result<List<User>> getAllUser(){
        return Result.success(adminService.getAllUser());
    }
    @RequestMapping("/shop")
    public Result<List<Shop>> getAllShop(){
        return Result.success(adminService.getAllShop());
    }
    @PutMapping("/update/shop")
    public Result<Shop> updateShop(@RequestBody Shop shop){
        return shopClient.update(shop);

    }
    @PutMapping("/update/user")
    public Result<User> updateUser(@RequestBody User user){
        return userClient.update(user);
    }
}
