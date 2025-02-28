package cn.edu.jlu.client;


import cn.edu.jlu.po.Address;
import cn.edu.jlu.po.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-user")
public interface AddressClient {
    @RequestMapping("/user/address/{id}")
    public Result<Address> findAddressById(@PathVariable("id") Integer id);

}
