package cn.edu.jlu.mapper;

import cn.edu.jlu.po.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AddressMapper extends BaseMapper<Address>{
    @Select("select * from addresses where user_id = #{userId}")
    List<Address> selectByUserId(Integer userId);
    @Select("select * from addresses where address = #{address}")
    Address selectByAddress(String address);


}
