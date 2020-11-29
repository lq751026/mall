package com.mall.mallgnorder.serivce;


import com.mall.mallgnorder.serivce.impl.Addressimpl;
import com.shoping.mallgn.entity.AddressEntity;
import com.shoping.mallgn.utile.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
@FeignClient(value = "mall-gn",fallback = Addressimpl.class) //value:服务命的提供者的   fallback异常处理
public interface Address {

    /**
     * 列表
     */
    @Cacheable(cacheNames ={"params"})
    @RequestMapping("/mallgn/address/list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @Cacheable(cacheNames ={"params"})
    @RequestMapping("/mallgn/address/info/{asId}")
    public R info(@PathVariable("asId") Integer asId);

    /**
     * 保存
     */
    @CachePut(value = "params",key = "#address.asId")
    @RequestMapping("/mallgn/address/save")
    public R save(@RequestBody AddressEntity address);

    /**
     * 修改
     */
    @CachePut(key = "#address.asId")
    @RequestMapping("/mallgn/address/update")
    public R update(@RequestBody AddressEntity address);

    /**
     * 删除
     */
    @CacheEvict(value = "params",key = "#asIds")
    @RequestMapping("/mallgn/address/delete")
    public R delete(@RequestBody Integer[] asIds);
}
