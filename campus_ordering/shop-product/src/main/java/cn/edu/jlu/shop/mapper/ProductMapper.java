package cn.edu.jlu.shop.mapper;

import cn.edu.jlu.po.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

public interface ProductMapper extends BaseMapper<Product> {
    @Select("select * from shop_product")
    Page<Product> pageQuery();
}