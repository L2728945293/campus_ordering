package cn.edu.jlu.service;


import cn.edu.jlu.po.Address;
import common.NewAddressBody;

import java.util.List;

public interface AddressService {
    Address findAddressById(Integer id);
    List<Address> findAddressByUserId(Integer userId);
    Address update(Address address);
    Address create(Address address);
    void delete(Address address);
}
