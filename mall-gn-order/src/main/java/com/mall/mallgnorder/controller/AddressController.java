package com.mall.mallgnorder.controller;


import com.mall.mallgnorder.serivce.Address;
import com.shoping.mallgn.entity.AddressEntity;
import com.shoping.mallgn.utile.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/address/order")
public class AddressController {

    @Autowired
    private Address address;

    /**
     * 列表
     */
    @RequestMapping("/mallgn/address/list")
    public R list(@RequestParam Map<String, Object> params){
        return address.list(params);
    }

    /**
     * 保存
     */
    @RequestMapping("/mallgn/address/save")
    public R save(@RequestBody AddressEntity address){
     return this.address.save(address);
    }

    /**
     * 修改
     */
    @RequestMapping("/mallgn/address/update")
    public R update(@RequestBody AddressEntity address){
     return this.address.update(address);
    }

    /**
     * 删除
     */
    @RequestMapping("/mallgn/address/delete")
    public R delete(@RequestBody Integer[] asIds){
        return this.address.delete(asIds);
    }



}
