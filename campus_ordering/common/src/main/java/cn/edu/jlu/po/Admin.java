package cn.edu.jlu.po;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("admins")
@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
