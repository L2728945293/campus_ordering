package cn.edu.jlu.shop.service;


import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);

    Product findById(Integer id);

    void deleteById(Integer id);

    void update(Product product);

    List<Product> findAllDish(Integer shopId);
}
