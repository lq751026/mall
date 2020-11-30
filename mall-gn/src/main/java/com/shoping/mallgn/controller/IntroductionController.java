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

import com.shoping.mallgn.entity.IntroductionEntity;
import com.shoping.mallgn.service.IntroductionService;
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
@RequestMapping("/mallgn/introduction")
public class IntroductionController {
    @Autowired
    private IntroductionService introductionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
            r = introductionService.listpage(params);
        }else{
            r= introductionService.listNoPage(params);
        }
        return r;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{inId}")
    public R info(@PathVariable("inId") Integer inId){
		IntroductionEntity introduction = introductionService.getById(inId);

        return R.ok().put("introduction", introduction);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "introduction",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody IntroductionEntity introduction){
		introductionService.save(introduction);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "introduction",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody IntroductionEntity introduction){
		introductionService.updateById(introduction);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "introduction",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] inIds){
		introductionService.removeByIds(Arrays.asList(inIds));

        return R.ok();
    }

}
