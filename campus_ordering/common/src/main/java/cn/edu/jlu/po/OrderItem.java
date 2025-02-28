package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@TableName("order_items")
@Data
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer order_id;
    private Integer dish_id;
    private String dish_name;
    private BigDecimal price;
    private Integer quantity;
}