package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoping.mallgn.entity.MerchantEntity;
import com.shoping.mallgn.service.MerchantService;
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
@RequestMapping("/mallgn/merchant")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = merchantService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{mtId}")
    public R info(@PathVariable("mtId") Integer mtId){
		MerchantEntity merchant = merchantService.getById(mtId);

        return R.ok().put("merchant", merchant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MerchantEntity merchant){
		merchantService.save(merchant);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MerchantEntity merchant){
		merchantService.updateById(merchant);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] mtIds){
		merchantService.removeByIds(Arrays.asList(mtIds));

        return R.ok();
    }

}
