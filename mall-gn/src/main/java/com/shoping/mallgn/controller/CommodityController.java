package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.shoping.mallgn.entity.MerchantEntity;
import com.shoping.mallgn.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoping.mallgn.entity.CommodityEntity;
import com.shoping.mallgn.service.CommodityService;
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
@RequestMapping("/mallgn/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 列表
     *     *    @Cacheable(value = "commodity",key = "'R'")
     *      *     @CacheEvict(value = "commodity",key = "'R'")
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
            r = commodityService.listpage(params);
        }else{
            r= commodityService.listNoPage(params);
        }
        return r;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{cmId}")
    public R info(@PathVariable("cmId") Integer cmId){
		CommodityEntity commodity = commodityService.getById(cmId);

        return R.ok().put("commodity", commodity);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "commodity",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody CommodityEntity commodity){
		commodityService.save(commodity);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "commodity",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody CommodityEntity commodity){
		commodityService.updateById(commodity);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "commodity",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] cmIds){
		commodityService.removeByIds(Arrays.asList(cmIds));

        return R.ok();
    }

}
