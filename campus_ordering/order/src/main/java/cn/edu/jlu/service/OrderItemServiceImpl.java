package cn.edu.jlu.service;

import cn.edu.jlu.mapper.OrderItemMapper;
import cn.edu.jlu.po.NewOrderItemBody;
import cn.edu.jlu.po.OrderItem;
import jakarta.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Resource
    private OrderItemMapper orderItemMapper;


    @Override
    public OrderItem findOrderItemById(Integer id) {
        return orderItemMapper.selectById(id);
    }

    @Override
    public List<OrderItem> findOrderItemByOrderId(Integer orderId) {

        return orderItemMapper.selectByOrderId(orderId);
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrder_id(newOrderItemBody.getOrderId());
//        orderItem.setDish_id(newOrderItemBody.getDishId());
//        orderItem.setDish_name(newOrderItemBody.getDishName());
//        orderItem.setPrice(newOrderItemBody.getPrice());
//        orderItem.setQuantity(newOrderItemBody.getQuantity());
        orderItemMapper.insert(orderItem);
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        orderItemMapper.updateById(orderItem);
        orderItem = orderItemMapper.selectById(orderItem.getId());
        return orderItem;
    }

    @Override
    public void delete(OrderItem orderItem) {
        try{
            int affectedRows = orderItemMapper.deleteById(orderItem.getId());
            if (affectedRows == 0) {
                throw new ServiceException("要删除的订单不存在，ID：" + orderItem.getId());
            }
        }catch(DataAccessException e){
            throw new ServiceException("订单删除失败，请重试");
        }
    }
}
