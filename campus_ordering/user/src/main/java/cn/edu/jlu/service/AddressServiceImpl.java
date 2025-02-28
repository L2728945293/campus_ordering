package cn.edu.jlu.service;

import cn.edu.jlu.mapper.AddressMapper;
import cn.edu.jlu.po.Address;
import common.NewAddressBody;
import jakarta.annotation.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AddressServiceImpl implements AddressService{
    @Resource
    private AddressMapper addressMapper;


    @Override
    public Address findAddressById(Integer id){
        return addressMapper.selectById(id);
    }

    @Override
    public List<Address> findAddressByUserId(Integer userId) {
        List<Address> addresses = addressMapper.selectByUserId(userId);
        return addresses;
    }

    @Override
    public Address update(Address address) {
        address.setUpdate_time(LocalDateTime.now());
//        String id = address.getId().toString();
//        address.setId(Integer.parseInt(id));
        addressMapper.updateById(address);

        address = addressMapper.selectById(address.getId());
        return address;
    }

    @Override
    public Address create(Address address) {
//        Address address = new Address();
//        address.setUser_id(newAddressBody.getUser_id());
//        address.setName(newAddressBody.getName());
//        address.setPhone(newAddressBody.getPhone());
//        address.setAddress(newAddressBody.getAddress());
//        address.setIs_default(newAddressBody.getIs_default());
        address.setCreate_time(LocalDateTime.now());
//        address.setUpdate_time(LocalDateTime.now());
        addressMapper.insert(address);
        return address;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void delete(Address address) {
        try {
            int affectedRows = addressMapper.deleteById(address.getId());
            if (affectedRows == 0) {
                throw new ServiceException("要删除的地址不存在，ID：" + address.getId());
            }
        } catch (DataAccessException e) {
            // logger.error("删除地址失败，地址ID: {}", address.getId(), e);
            throw new ServiceException("地址删除失败，请重试");
        }
    }

}
