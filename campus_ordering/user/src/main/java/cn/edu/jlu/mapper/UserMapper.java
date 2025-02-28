package cn.edu.jlu.mapper;

import cn.edu.jlu.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
 *  1、 接口UserMapper 必须继承BaseMapper
 *  2、BaseMapper< User > :User :  具体的实体类
 *  3、BaseMapper封装了持久化操作的各种方法，包括插入、更新、删除、查询和分页等
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from users where username = #{username}")
    User selectByUsername(String username);
    @Select("select * from users")
    List<User> selectAll();
}