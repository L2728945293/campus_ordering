package cn.edu.jlu.controller;


import cn.edu.jlu.po.Address;
import cn.edu.jlu.service.AddressService;
import common.NewAddressBody;
import common.Result;
import jakarta.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Resource
    private AddressService addressService;
    @RequestMapping("/{id}")
    public Result<Address> findAddressById(@PathVariable("id") Integer id) {
        Result<Address> R = new Result<Address>();
        R.setData(addressService.findAddressById(id));
        return Result.success(R.getData());

    }

    @RequestMapping("/user/{userId}")
    public Result<List<Address>> findAddressByUserId(@PathVariable("userId") Integer userId) {
        Result<List<Address>> R = new Result<List<Address>>();
        R.setData(addressService.findAddressByUserId(userId));
        return Result.success(R.getData());
    }

    @PostMapping("/create")
    public Result<Address> create(@RequestBody Address address){
        Result<Address> R = new Result<Address>();
        return Result.success(addressService.create(address));
    }

    @PutMapping("/update")
    public Result<Address> update(@RequestBody Address address){
        Result<Address> R = new Result<Address>();
        return Result.success(addressService.update(address));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody Address address){
        try{
            addressService.delete(address);
            return Result.success("删除成功");
        }
        catch(ServiceException e){
            return Result.error(400, e.getMessage());
        }

    }

}
