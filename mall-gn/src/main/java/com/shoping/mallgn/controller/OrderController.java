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

import com.shoping.mallgn.entity.OrderEntity;
import com.shoping.mallgn.service.OrderService;
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
@RequestMapping("/mallgn/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
            r = orderService.listpage(params);
        }else{
            r= orderService.listNoPage(params);
        }
        return r;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{orUuid}")
    public R info(@PathVariable("orUuid") String orUuid){
		OrderEntity order = orderService.getById(orUuid);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "order",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "order",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "order",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] orUuids){
		orderService.removeByIds(Arrays.asList(orUuids));

        return R.ok();
    }

}
