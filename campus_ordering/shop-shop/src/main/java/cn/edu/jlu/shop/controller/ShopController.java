package cn.edu.jlu.shop.controller;


import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.*;
import cn.edu.jlu.constant.JwtClaimsConstant;
import cn.edu.jlu.properties.JwtProperties;
import cn.edu.jlu.utils.JwtUtil;
import cn.edu.jlu.shop.service.ShopService;
//import com.alibaba.nacos.api.model.v2.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 商家控制层 注册登陆
 */
@CrossOrigin
@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {
    @Resource
    private ShopService shopService;

    /**
     * 注册
     */
    @PostMapping("/shop/register")
    public Result register(@RequestBody Shop shop) {
        shopService.register(shop);
        log.info("/shop/shop/register");
        return Result.success();
    }
    /**
     * 登录
     *
     */
    @PostMapping("/shop/login")
    public Result<Shop> login(@RequestBody Shop shop) { // 添加参数校验注解
        shopService.login(shop);
        log.info("/shop/shop/login");
        return Result.success(shop); // 修正返回对象
    }



    /**
     * 查询店铺信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result<Shop> info(@PathVariable("id") Integer id) {
        Shop shop = shopService.findShopById(id);
        log.info("/shop/info");
        return Result.success(shop);
    }

    /**
     * 修改店铺信息
     * @param shop
     * @return
     */
    @PutMapping("/info/update")
    public Result<Shop> update(@RequestBody Shop shop) {
        shopService.updateshop(shop);//后绪步骤实现
        log.info("/shop/info/update");
        return Result.success(shop);
    }
    /**
     * 查询订单
     */
    @GetMapping("/order/{id}")
    public Result<Order> selectorder(@PathVariable("id") Integer id) {
        log.info("/shop/order/selectorder");
        return Result.success(shopService.selectorder(id));
    }
    @DeleteMapping("/order/{id}")
    public Result deleteorder(@PathVariable("id") Integer id) {
        log.info("/shop/order/deleteorder");
        shopService.deleteOrder(id);
        return Result.success();
    }

    @PutMapping("/order/update")
    public Result updateorder(@RequestBody Order order) {
        log.info("/shop/order/update");
        shopService.updateOrder(order);
        return Result.success();
    }

    @RequestMapping("/list")
    public Result<List<Shop>> findAllShop() {
        log.info("/shop/list");
        return Result.success(shopService.findAllShop());
    }
    @GetMapping("/order/all/{shopId}")
    public Result<List<Order>> findAllOrder(@PathVariable("shopId") Integer shopId) {
        log.info("/shop/order/all");
        return Result.success(shopService.findAllOrder(shopId));
    }
    @RequestMapping("/turnover")
    public Result<BigDecimal> getShopTurnover(@RequestBody TurnOverBody turnOverBody) {
        log.info("/shop/turnover");
        return Result.success(shopService.getShopTurnover(turnOverBody));
    }
    //菜品分页
    @PostMapping("/dish/page")
    public Result<PageResult> pageProduct(@RequestBody PagePo pagePo){
        return Result.success(shopService.findProductByPage(pagePo));
    }
    //商家分页
    @PostMapping("/shop/page")
    public Result<PageResult> pageShop(@RequestBody PagePo pagePo){
        return Result.success(shopService.findShopByPage(pagePo));
    }

}
