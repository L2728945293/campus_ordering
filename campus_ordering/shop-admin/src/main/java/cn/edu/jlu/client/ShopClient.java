package cn.edu.jlu.client;

import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.Shop;
//import com.alibaba.nacos.api.model.v2.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-shop")
public interface ShopClient {
    @PostMapping("/shop/shop/register")
    public Result register(@RequestBody Shop shop);
    @GetMapping("/shop/info/{id}")
    public Result<Shop> info(@RequestParam("id") Integer id);
    @PutMapping("/shop/info/update")
    public Result<Shop> update(@RequestBody Shop shop);
    @RequestMapping("/shop/list")
    public Result<List<Shop>> findAllShop();
}
