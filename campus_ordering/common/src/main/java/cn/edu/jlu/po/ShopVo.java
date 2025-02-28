package cn.edu.jlu.po;

import lombok.*;
import org.springframework.core.serializer.Serializer;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopVo implements Serializable {
    //("主键值")
    private Integer id;

    ///("用户名")
    private String userName;

    //("姓名")
    private String name;

    //("jwt令牌")
    private String token;
}