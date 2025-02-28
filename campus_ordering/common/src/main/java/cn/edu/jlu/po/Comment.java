package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("comments")
@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer order_id; //
    private Integer user_id;
    private Integer shop_id;
    private Integer shop_rating;  // Use Integer
    private Integer delivery_rating; // Use Integer
    private String shop_comment;
    private String delivery_comment;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime create_time;
}