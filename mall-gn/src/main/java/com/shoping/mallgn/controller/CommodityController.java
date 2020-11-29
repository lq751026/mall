package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.shoping.mallgn.entity.MerchantEntity;
import com.shoping.mallgn.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
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
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commodityService.queryPage(params);
        return R.ok().put("page", page);
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
    @RequestMapping("/save")
    public R save(@RequestBody CommodityEntity commodity){
		commodityService.save(commodity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommodityEntity commodity){
		commodityService.updateById(commodity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] cmIds){
		commodityService.removeByIds(Arrays.asList(cmIds));

        return R.ok();
    }

}
