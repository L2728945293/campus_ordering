package cn.edu.jlu.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dish_categories")
@Data
public class DishCategory {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer shop_id;
    private String name;
}