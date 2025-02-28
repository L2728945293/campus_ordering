package cn.edu.jlu.mapper;

import cn.edu.jlu.po.Order;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    @Select("select * from orders where user_id = #{userId}")
    List<Order> findOrderByUserId(Integer userId);
    @Select("select * from orders where id = #{id}")
    Order findOrderById(Integer id);
    @Select("select * from orders where shop_id = #{shopId}")
    List<Order> findOrderByShopId(Integer shopId);

    // 查询超时未支付的订单（status=0 且 create_time <= deadline）
    default List<Order> selectTimeoutOrders(LocalDateTime deadline) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getStatus, 0)
                .le(Order::getCreate_time, deadline); // create_time <= deadline
        return selectList(queryWrapper);
    }

    // 批量更新订单状态（避免频繁单条更新）
//    @Update("UPDATE `orders` SET status = #{newStatus}, update_time = NOW() " +
//            "WHERE id IN (${orderIds}) AND status = 0") // 乐观锁：确保状态未变
//    int batchUpdateStatus(String orderIds, Integer newStatus);
    int batchUpdateStatus(@Param("orderIds") List<String> orderIds, @Param("newStatus") int newStatus);
}
