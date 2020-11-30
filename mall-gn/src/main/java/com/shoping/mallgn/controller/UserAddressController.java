package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoping.mallgn.entity.UserAddressEntity;
import com.shoping.mallgn.service.UserAddressService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author lq
 * @email 
 * @date 2020-11-26 14:42:28
 */
@RestController
@RequestMapping("/mallgn/useraddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    /**
     * 列表
     */

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
            r = userAddressService.listpage(params);
        }else{
            r= userAddressService.listNoPage(params);
        }
        return r;
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{uaId}")
    public R info(@PathVariable("uaId") Integer uaId){
		UserAddressEntity userAddress = userAddressService.getById(uaId);

        return R.ok().put("userAddress", userAddress);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "useraddress",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody UserAddressEntity userAddress){
		userAddressService.save(userAddress);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "useraddress",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody UserAddressEntity userAddress){
		userAddressService.updateById(userAddress);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "useraddress",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] uaIds){
		userAddressService.removeByIds(Arrays.asList(uaIds));

        return R.ok();
    }

}
