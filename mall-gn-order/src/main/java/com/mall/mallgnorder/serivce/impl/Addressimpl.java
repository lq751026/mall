package com.mall.mallgnorder.serivce.impl;

import com.mall.mallgnorder.serivce.Address;
import com.shoping.mallgn.entity.AddressEntity;
import com.shoping.mallgn.utile.R;

import java.util.Map;

public class Addressimpl implements Address {
    @Override
    public R list(Map<String, Object> params) {
        return new R().put("msg","出现异常/(ㄒoㄒ)/~~");
    }

    @Override
    public R info(Integer asId) {
        return null;
    }

    @Override
    public R save(AddressEntity address) {
        return null;
    }

    @Override
    public R update(AddressEntity address) {
        return null;
    }

    @Override
    public R delete(Integer[] asIds) {
        return null;
    }
}
