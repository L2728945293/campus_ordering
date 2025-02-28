package cn.edu.jlu.po;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewCartBody {
    private Integer user_id;
    private Integer shop_id;
    private Integer dish_id;
    private Integer quantity;
    private BigDecimal price;
}
