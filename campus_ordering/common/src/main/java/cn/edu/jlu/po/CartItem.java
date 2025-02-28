package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartItem {
    //cart info
    Cart cart;
    //shop info
    Shop shop;
    //dish info
    Product product;
}
