package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("addresses")
@Data
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer user_id;
    private String name;
    private String phone;
    private String address;
    private Integer is_default; // Use Integer (0 or 1)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
}