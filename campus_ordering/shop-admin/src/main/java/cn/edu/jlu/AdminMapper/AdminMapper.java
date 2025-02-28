package cn.edu.jlu.AdminMapper;

import cn.edu.jlu.po.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from users where username = #{username}")
    Admin selectByUsername(String username);
}
