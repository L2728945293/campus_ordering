package cn.edu.jlu.service;

import cn.edu.jlu.mapper.UserMapper;
import cn.edu.jlu.po.User;
import common.LoginBody;
import common.RegisterBody;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper mapper ;

    @Override
    public User findUserById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public User login(LoginBody loginBody) {
        User user1 = mapper.selectByUsername(loginBody.getUsername());
        if (user1 == null) {
            return null;
        }
        if (!user1.getPassword().equals(loginBody.getPassword())) {
            return null;
        }
        return user1;
        // return null means username or password is wrong
    }

    @Override
    public User register(RegisterBody registerBody) {
        User user1 = mapper.selectByUsername(registerBody.getUsername());
        if (user1 != null) {
            return null;
        }
        User user = new User();
        user.setUsername(registerBody.getUsername());
        user.setPassword(registerBody.getPassword());
        user.setNickname(registerBody.getNickname());
        user.setAvatar(registerBody.getAvatar());
        user.setPhone(registerBody.getPhone());
        user.setCreate_time(LocalDateTime.now());
        user.setUpdate_time(LocalDateTime.now());
        mapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        user.setUpdate_time(LocalDateTime.now());
        mapper.updateById(user);
        user = mapper.selectById(user.getId());
        return user;
    }

    @Override
    public List<User> findAllUser() {
        return mapper.selectAll();
    }
}