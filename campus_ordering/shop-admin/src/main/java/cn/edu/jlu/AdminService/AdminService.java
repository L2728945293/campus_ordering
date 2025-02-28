package cn.edu.jlu.AdminService;

import cn.edu.jlu.po.Admin;
import cn.edu.jlu.po.Shop;
import cn.edu.jlu.po.User;

import java.util.List;

public interface AdminService {
    Admin login(Admin admin);
    Admin register(Admin admin);
    List<User> getAllUser();
    List<Shop> getAllShop();
}
