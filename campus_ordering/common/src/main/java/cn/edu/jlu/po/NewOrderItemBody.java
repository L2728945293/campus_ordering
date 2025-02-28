package cn.edu.jlu.po;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewOrderItemBody {
    private Integer orderId;
    private Integer dishId;
    private String dishName;
    private BigDecimal price;
    private Integer quantity;
}
