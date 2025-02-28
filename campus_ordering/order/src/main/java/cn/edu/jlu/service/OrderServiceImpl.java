package cn.edu.jlu.service;


import cn.edu.jlu.client.AddressClient;
import cn.edu.jlu.mapper.OrderMapper;
import cn.edu.jlu.po.Address;
import cn.edu.jlu.po.NewOrderBody;
import cn.edu.jlu.po.Order;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressClient addressClient;

    @Override
    public Order findOrderById(Integer id) {
        return orderMapper.findOrderById(id);
    }


    @Override
    public Order create(Order order) {
        order.setCreate_time(LocalDateTime.now());
        order.setUpdate_time(LocalDateTime.now());
        orderMapper.insert(order);
        return order;
    }

    @Override
    public Order update(Order order) {
        order.setUpdate_time(LocalDateTime.now());
        orderMapper.updateById(order);
        order = orderMapper.selectById(order.getId());
        return order;
    }

    @Override
    public void delete(Order order) {
        try{
            int affectedRows = orderMapper.deleteById(order.getId());
            if (affectedRows == 0) {
                throw new ServiceException("要删除的订单不存在，ID：" + order.getId());
            }
        }catch(DataAccessException e){
            throw new ServiceException("订单删除失败，请重试");
        }
    }

    @Override
    public List<Order> findOrderByUserId(Integer userId) {
        List<Order> orders = orderMapper.findOrderByUserId(userId);
        return orders;
    }

    @Override
    public List<Order> findOrderByShopId(Integer shopId) {
        List<Order> orders = orderMapper.findOrderByShopId(shopId);
        return orders;
    }

    @Override
    public BigDecimal getShopTurnover(Integer shopId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Order> orders = orderMapper.findOrderByShopId(shopId);
        BigDecimal turnover = orders.stream()
                .filter(order -> order.getCreate_time().isAfter(startTime) && order.getCreate_time().isBefore(endTime) && order.getStatus() == 4)
                .map(Order::getTotal_price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return turnover;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findTimeoutOrders(LocalDateTime deadline) {
//        LocalDateTime deadline = LocalDateTime.now().minusMinutes(15);
        return orderMapper.selectTimeoutOrders(deadline);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(cron = "0 0/1 * * * ?")
    @Scheduled(fixedRate = 5000 * 12 * 10)
    public void cancelTimeoutOrders() {
        LocalDateTime deadline = LocalDateTime.now().minusMinutes(10);
        List<Order> timeoutOrders = findTimeoutOrders(deadline);
        if (timeoutOrders.isEmpty()) {
            log.info("无超时未支付订单");
            return;
        }
        log.info("123");
        for (Order order : timeoutOrders) {
            try {
                // 4. 再次检查状态（防止重复处理）
                Order latestOrder = orderMapper.selectById(order.getId());
                if (latestOrder != null && latestOrder.getStatus() == 0) {
                    // 5. 更新状态为取消
                    latestOrder.setStatus(5);
                    orderMapper.updateById(latestOrder);
                    log.debug("订单已取消: {}", order.getId());

                    // 可选：触发后续操作（如退款通知）
                    // notificationService.sendCancelNotice(order.getId());
                }
            } catch (Exception e) {
                log.error("处理订单失败: {}", order.getId(), e);
                // 可选：记录失败订单，后续补偿
            }
        }

    }

    @Override
    public void deleteById(Integer id) {
        try{
            int affectedRows = orderMapper.deleteById(id);
            if (affectedRows == 0) {
                throw new ServiceException("要删除的订单不存在，ID：" + id);
            }
        }catch(DataAccessException e){
            throw new ServiceException("订单删除失败，请重试");
        }
    }

    @Override
    public Address getAddressByOrderId(Integer addressId) {
        return addressClient.findAddressById(addressId).getData();
    }
}
