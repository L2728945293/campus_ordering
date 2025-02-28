package cn.edu.jlu.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("cart_items")
@Data
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user_id;
    private Integer shop_id;
    private Integer dish_id;
    private Integer quantity;
    private BigDecimal price;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}
