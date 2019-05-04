package com.hilkr.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hilkr.auth.entity.UserInfo;
import com.hilkr.dal.dao.AddressMapper;
import com.hilkr.dal.model.Address;
import com.hilkr.order.interceptor.LoginInterceptor;
import com.hilkr.order.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 98050
 * @Time: 2018-10-31 09:44
 * @Feature:
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void deleteAddress(Long addressId) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userInfo.getId())
                .eq("id", addressId);
        this.addressMapper.delete(queryWrapper);
    }

    @Override
    public void updateAddressByUserId(Address address) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        address.setUserId(userInfo.getId());
        setDefaultAddress(address);
        this.addressMapper.updateByPrimaryKeySelective(address);

    }

    @Override
    public List<Address> queryAddressByUserId() {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        return this.addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", userInfo.getId()));
    }

    @Override
    public void addAddressByUserId(Address address) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        address.setUserId(userInfo.getId());
        setDefaultAddress(address);
        this.addressMapper.insert(address);
    }

    @Override
    public Address queryAddressById(Long addressId) {
        UserInfo userInfo = LoginInterceptor.getLoginUser();
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", addressId)
                .eq("user_id", userInfo.getId());
        return this.addressMapper.selectList(queryWrapper).get(0);
    }

    public void setDefaultAddress(Address address) {
        if (address.getDefaultAddress()) {
            //如果将本地址设置为默认地址，那么该用户下的其他地址都应该是非默认地址
            List<Address> addressList = this.queryAddressByUserId();
            addressList.forEach(addressTemp -> {
                if (addressTemp.getDefaultAddress()) {
                    addressTemp.setDefaultAddress(false);
                    this.addressMapper.updateByPrimaryKeySelective(addressTemp);
                }
            });
        }
    }
}
