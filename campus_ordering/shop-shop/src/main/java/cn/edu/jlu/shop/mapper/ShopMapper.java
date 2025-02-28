package cn.edu.jlu.shop.mapper;

//import cn.edu.jlu.common.Shop;
import cn.edu.jlu.po.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShopMapper extends BaseMapper<Shop> {
    @Select("select * from shops")
    List<Shop> selectAll();
}
