package cn.edu.jlu.client;


import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.Shop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-shop")
public interface ShopClient {

    @GetMapping("/shop/info/{id}")
    public Result<Shop> info(@RequestParam("id") Integer id);
}
