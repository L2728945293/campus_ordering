package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("shops")
@Data
public class Shop {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String shop_name;
    private String username;
    private String password;
    private String logo_url;
    private String business_hours;
    private String notice;
    private Integer status; // Use Integer
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_time;
    @TableField("category_id")
    private Integer category_id;
}