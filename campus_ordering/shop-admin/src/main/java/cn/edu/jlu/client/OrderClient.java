package cn.edu.jlu.client;


import cn.edu.jlu.po.Result;
import cn.edu.jlu.po.TurnOverBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@FeignClient("service-order")
public interface OrderClient {
    @RequestMapping("/order/shop/turnover")
    public Result<BigDecimal> getShopTurnover(@RequestBody TurnOverBody turnOverBody);
}
