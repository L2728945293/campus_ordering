package cn.edu.jlu.shop.service;

import cn.edu.jlu.page.PageResult;
import cn.edu.jlu.po.PagePo;
import cn.edu.jlu.po.Product;
import cn.edu.jlu.shop.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public Product findProductById(Integer uid) {
        return productMapper.selectById(uid);
    }

    @Override
    public void saveProduct(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void deleteProduct(Integer pid) {
        productMapper.deleteById(pid);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateById(product);
    }

    @Override
    public PageResult findProductByPage(PagePo pagePo) {
        if(pagePo.getPage()!=null && pagePo.getSize()!=null)
            PageHelper.startPage(pagePo.getPage(), pagePo.getSize());
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (pagePo.stasus != null) {
            queryWrapper.eq("status", pagePo.stasus);
        }
        if (pagePo.cateId != null) {
            queryWrapper.eq("category_id", pagePo.cateId);
        }
        List<Product> products =  productMapper.selectList(queryWrapper);
        PageInfo<Product> pageInfo = new PageInfo<>(products);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public List<Product> findAllProduct(Integer shopid) {
        return productMapper.selectList(new QueryWrapper<Product>().eq("shop_id", shopid));
    }


}