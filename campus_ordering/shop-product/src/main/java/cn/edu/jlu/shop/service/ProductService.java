package cn.edu.jlu.shop.service;


import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.PagePo;
import cn.edu.jlu.po.Product;

import java.util.List;

public interface ProductService {
    Product findProductById(Integer uid);

    void saveProduct(Product product);

    void deleteProduct(Integer pid);

    void updateProduct(Product product);

    PageResult findProductByPage(PagePo pagepo);

    List<Product> findAllProduct(Integer shopid);
}