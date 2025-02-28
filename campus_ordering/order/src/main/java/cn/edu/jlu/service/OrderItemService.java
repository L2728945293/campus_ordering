package cn.edu.jlu.service;

import cn.edu.jlu.po.NewOrderItemBody;
import cn.edu.jlu.po.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem findOrderItemById(Integer id);
    List<OrderItem> findOrderItemByOrderId(Integer orderId);
    OrderItem create(OrderItem orderItem);
    OrderItem update(OrderItem orderItem);
    void delete(OrderItem orderItem);
}
