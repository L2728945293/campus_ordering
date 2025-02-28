package cn.edu.jlu.AdminService;

import cn.edu.jlu.AdminMapper.AdminMapper;
import cn.edu.jlu.client.ShopClient;
import cn.edu.jlu.client.UserClient;
import cn.edu.jlu.po.Admin;
import cn.edu.jlu.po.Shop;
import cn.edu.jlu.po.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Resource
    private AdminMapper adminMapper;

    @Resource
    private ShopClient shopClient;

    @Resource
    private UserClient userClient;

    @Override
    public Admin login(Admin admin) {
        admin = adminMapper.selectByUsername(admin.getUsername());
        if (admin == null) {
            return null;
        }
        if (!admin.getPassword().equals(admin.getPassword())) {
            return null;
        }
        return admin;
    }

    @Override
    public Admin register(Admin admin) {
//        admin.setCreate_time(LocalDateTime.now());
        adminMapper.insert(admin);
        return admin;
    }

    @Override
    public List<User> getAllUser() {
        return userClient.findAllUser();
    }

    @Override
    public List<Shop> getAllShop() {
        return shopClient.findAllShop().getData();
    }
}
