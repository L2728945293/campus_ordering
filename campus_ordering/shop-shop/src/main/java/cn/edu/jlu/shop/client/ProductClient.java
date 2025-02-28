package cn.edu.jlu.shop.client;

//import cn.edu.jlu.shop.po.common.Product;
import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.PagePo;
import cn.edu.jlu.po.Product;
import cn.edu.jlu.po.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-product")
public interface ProductClient {
    @PostMapping("/product/save")
    public Result save(@RequestBody Product product);

    @GetMapping("/product/select/{pid}")
    public Result<Product> findById(@PathVariable("pid") Integer pid);

    @DeleteMapping("/product/delete/{pid}")
    public Result deleteById(@PathVariable("pid") Integer pid);

    @PutMapping("/product/update")
    public Result update(@RequestBody Product product);
    @PostMapping("/product/select/page")
    public Result<PageResult> findProductByPage(@RequestBody PagePo pagePo);
    @GetMapping("/product/select/all/{shopid}")
    public Result<List<Product>> findAllDish(@PathVariable("shopid") Integer shopid);
}
