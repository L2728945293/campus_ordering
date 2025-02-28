package cn.edu.jlu.shop.service;

import cn.edu.jlu.po.Order;
import cn.edu.jlu.po.Product;
import cn.edu.jlu.shop.client.ProductClient;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Resource
    private ProductClient productClient;
    @Override
    public void save(Product product) {
        productClient.save(product);
        return;
    }

    @Override
    public Product findById(Integer id) {
        Product product= productClient.findById(id).getData();
        if(product==null){
            throw new RuntimeException("商品不存在");
        }
        return product;
    }
    public void deleteById(Integer id) {
        productClient.deleteById(id);
        return;
    }
    public void update(Product product) {
        productClient.update(product);
        return;
    }

    @Override
    public List<Product> findAllDish(Integer shopid) {
        return productClient.findAllDish(shopid).getData();
    }
}
