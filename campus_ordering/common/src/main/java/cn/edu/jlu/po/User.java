package cn.edu.jlu.po;

//MybatisPlus ，帮助我们完成单表的CRUD
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;

@TableName("users")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
//    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String username;
//    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String password;
//    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String nickname;
//    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String phone;
//    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private String avatar;
//    @TableField(updateStrategy = FieldStrategy.NOT_NULL)
    private Integer status;  // Use Integer, not int, to allow for null
    @TableField(fill = FieldFill.INSERT) // Auto-fill on insert
    private LocalDateTime create_time;
    @TableField(fill = FieldFill.INSERT_UPDATE) // Auto-fill on insert and update
    private LocalDateTime update_time;
}