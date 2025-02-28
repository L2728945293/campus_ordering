package cn.edu.jlu.po;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewOrderBody {
    private Integer userId;
    private Integer shopId;
    private Integer addressId;
    private BigDecimal totalPrice;
    private Integer status; // Use Integer
    private String remark;
}
