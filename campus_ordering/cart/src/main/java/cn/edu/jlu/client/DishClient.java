package cn.edu.jlu.client;


import cn.edu.jlu.po.Product;
import com.alibaba.nacos.api.model.v2.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-product")
public interface DishClient {
    @GetMapping("/product/select/{pid}")
    public Result<Product> findProductById(@PathVariable("pid") Integer pid);
}
