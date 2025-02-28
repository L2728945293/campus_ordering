package cn.edu.jlu.service;

import cn.edu.jlu.po.User;
import common.LoginBody;
import common.RegisterBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    User findUserById(Integer id);
    User login(@RequestBody LoginBody loginBody);
    User register(@RequestBody RegisterBody registerBody);
    User update(@RequestBody User user);

    List<User> findAllUser();
}