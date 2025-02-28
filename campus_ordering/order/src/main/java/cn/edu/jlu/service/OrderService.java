package cn.edu.jlu.service;

import cn.edu.jlu.po.Address;
import cn.edu.jlu.po.NewOrderBody;
import cn.edu.jlu.po.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order findOrderById(Integer id);
    Order create(Order order);
    Order update(Order order);
    void delete(Order order);
    List<Order> findOrderByUserId(Integer userId);
    List<Order> findOrderByShopId(Integer shopId);
    BigDecimal getShopTurnover(Integer shopId, LocalDateTime startTime, LocalDateTime endTime);
    List<Order> findTimeoutOrders(LocalDateTime deadline);
    void cancelTimeoutOrders();

    void deleteById(Integer id);

    Address getAddressByOrderId(Integer addressId);
}
