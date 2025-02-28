package cn.edu.jlu.shop.controller;


import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.PagePo;
import cn.edu.jlu.po.Product;
import cn.edu.jlu.shop.service.ProductService;
import com.alibaba.nacos.api.model.v2.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 查找菜品
     * @param pid
     * @return
     */
    @GetMapping("/select/{pid}")
    public Result<Product> findProductById(@PathVariable("pid") Integer pid) {
        return Result.success(productService.findProductById(pid));
    }
    //查找所有菜品
    @GetMapping("/select/all/{shopid}")
    public Result<List<Product>> findAllProduct(@PathVariable("shopid") Integer shopid) {
        return Result.success(productService.findAllProduct(shopid));
    }
    /**
     * 增加菜品
     */
    @PostMapping("/save")
    public Result saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return Result.success();
    }
    /**
     * 删除菜品
     */
    @DeleteMapping("/delete/{pid}")
    public Result deleteProduct(@PathVariable("pid") Integer pid) {
        productService.deleteProduct(pid);
        return Result.success();
    }
    /**
     * 更新菜品
     */
    @PutMapping("/update")
    public Result updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return Result.success();
    }
    /**
     * 菜品分页查询
     */
    @PostMapping("select/page")
    public Result<PageResult> findProductByPage(@RequestBody PagePo pagepo) {
        return Result.success(productService.findProductByPage(pagepo));
    }

}