package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("dishes")
@Data
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer shop_id;
    private Integer category_id;
    private String name;
    private BigDecimal price;  // Use BigDecimal for monetary values
    private String image_url;
    private String description;
    private Integer status; // Use Integer
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}