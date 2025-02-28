package cn.edu.jlu.mapper;

import cn.edu.jlu.po.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {
    @Select("SELECT * FROM cart_items WHERE user_id = #{userId}")
    List<Cart> findCartByUserId(Integer userId);
    @Select("SELECT * FROM cart_items where user_id = #{userId} and shop_id = #{shopId} and dish_id = #{dishId}")
    Cart selectByUserIdShopIdDishId(@Param("userId") Integer userId, @Param("shopId") Integer shopId, @Param("dishId") Integer dishId);
}
