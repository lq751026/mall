package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoping.mallgn.entity.SortEntity;
import com.shoping.mallgn.service.SortService;
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
@RequestMapping("/mallgn/sort")
public class SortController {
    @Autowired
    private SortService sortService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
             r = sortService.listpage(params);
        }else{
            r= sortService.listNoPage(params);
        }

        return r;
    }

    //listWithThree

    /**
     * 获取列表
     */
    @RequestMapping("/list/tree")

    public R listtree(){
        List<SortEntity> list = sortService.listWithThree();
        return R.ok().put("data", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stId}")
    public R info(@PathVariable("stId") Integer stId){
		SortEntity sort = sortService.getById(stId);

        return R.ok().put("sort", sort);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "sort",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody SortEntity sort){
		sortService.save(sort);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "sort",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody SortEntity sort){
		sortService.updateById(sort);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "sort",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] stIds){
		sortService.removeByIds(Arrays.asList(stIds));

        return R.ok();
    }

}
