package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("orders")
@Data
public class Order {
    @TableId(type = IdType.ASSIGN_UUID) // Use UUID for distributed IDs
    private Integer id; // String, not Integer
    private Integer user_id;
    private Integer shop_id;
    private Integer address_id;
    private BigDecimal total_price;
    private Integer status; // Use Integer
    private String remark;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}