package cn.edu.jlu.shop.service;


import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.*;

import java.math.BigDecimal;
import java.util.List;


public interface ShopService {
    void register(Shop shop);

    Shop login(Shop shop);

    void updateshop(Shop shop);

    Shop findShopById(Integer id);


    void deleteOrder(Integer id);



    void updateOrder(Order order);

    Order selectorder(Integer id);

    List<Shop> findAllShop();

    BigDecimal  getShopTurnover(TurnOverBody turnOverBody);

    List<Order> findAllOrder(Integer shopId);

    PageResult findProductByPage(PagePo pagePo);

    PageResult findShopByPage(PagePo pagePo);
}
