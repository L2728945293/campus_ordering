package cn.edu.jlu.shop.controller;


import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Product;
import cn.edu.jlu.po.Result;
import cn.edu.jlu.shop.service.ProductService;
//import com.alibaba.nacos.api.model.v2.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商家的菜品管理
 */
@CrossOrigin
@RestController
@RequestMapping("/shop/dish")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    /**
     * 添加菜品
     */
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.success();
    }

    /**
     * 查找菜品
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Result<Product> info(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return Result.success(product);
    }
    /**
     *删除菜品
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        productService.deleteById(id);
        return Result.success();
    }
    /**
     * 修改菜品
     */
    @PutMapping("/update")
    public Result update(@RequestBody Product product) {
        productService.update(product);
        return Result.success();
    }
    @GetMapping("/select/all/{shopid}")
    public Result<List<Product>> findAllDish(@PathVariable("shopid") Integer shopid) {

        List<Product> products = productService.findAllDish(shopid);
        log.info("/shop/dish/select/all" + products);
        return Result.success(products);
    }

}
