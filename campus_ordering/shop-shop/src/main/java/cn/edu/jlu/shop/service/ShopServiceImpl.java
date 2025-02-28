package cn.edu.jlu.shop.service;

import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.*;
import cn.edu.jlu.shop.client.OrderClient;
import cn.edu.jlu.shop.client.ProductClient;
import cn.edu.jlu.shop.mapper.ShopMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Resource
    private ShopMapper shopMapper;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private OrderClient orderClient;

    public void register(Shop shop) {
            shop.setCreate_time(LocalDateTime.now());
            shop.setUpdate_time(LocalDateTime.now());
            shop.setStatus(1);
            shopMapper.insert(shop);
    }

    public Shop login(Shop shop) {
        Shop shop1 = shopMapper.selectOne(new QueryWrapper<Shop>()
                .eq("username", shop.getUsername()));  // 改为根据username查询

        if (shop1 == null) {
            throw new RuntimeException("用户名或密码错误");  // 统一提示更安全
        }
        if (shop1.getPassword().equals(shop.getPassword())) {
            shop.setId(shop1.getId());
            return shop1;
        }
        throw new RuntimeException("用户名或密码错误");

    }
    public void updateshop(Shop shop) {
        shop.setUpdate_time(LocalDateTime.now());
        shopMapper.updateById(shop);
    }
    public  Shop findShopById(Integer id) {
        return shopMapper.selectById(id);
    }



    @Override
    public void deleteOrder(Integer id) {
        orderClient.deleteOrderById(id);
    }



    @Override
    public void updateOrder(Order order) {
        orderClient.updateOrder(order);
    }

    @Override
    public Order selectorder(Integer id) {
        return orderClient.findOrderById(id).getData();
    }

    @Override
    public List<Shop> findAllShop() {
        return shopMapper.selectAll();
    }

    @Override
    public BigDecimal getShopTurnover(TurnOverBody turnOverBody) {
        return orderClient.getShopTurnover(turnOverBody).getData();
    }
    @Override
    public List<Order> findAllOrder(Integer shopId) {
        return  orderClient.findOrderByShopId(shopId).getData();
    }

    @Override
    public PageResult findProductByPage(PagePo pagePo) {
        return productClient.findProductByPage(pagePo).getData();
    }

    @Override
    public PageResult findShopByPage(PagePo pagePo) {
        if(pagePo.getPage()!=null && pagePo.getSize()!=null)
            PageHelper.startPage(pagePo.getPage(), pagePo.getSize());
        QueryWrapper<Shop> queryWrapper = new QueryWrapper<>();
        if (pagePo.stasus != null) {
            queryWrapper.eq("status", pagePo.stasus);
        }
        if (pagePo.cateId != null) {
            queryWrapper.eq("category_id", pagePo.cateId);
        }
        List<Shop> shop =  shopMapper.selectList(queryWrapper);
        PageInfo<Shop> pageInfo = new PageInfo<>(shop);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

}
