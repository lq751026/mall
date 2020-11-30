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
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")||page1==null){
            r = merchantService.listpage(params);
        }else{
            r= merchantService.listNoPage(params);
        }
        return r;
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
    @CacheEvict(value = "merchant",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody MerchantEntity merchant){
		merchantService.save(merchant);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "merchant",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody MerchantEntity merchant){
		merchantService.updateById(merchant);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "merchant",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] mtIds){
		merchantService.removeByIds(Arrays.asList(mtIds));

        return R.ok();
    }

}
