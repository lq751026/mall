package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
        PageUtils page = sortService.queryPage(params);
        return R.ok().put("page", page);
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
    @RequestMapping("/save")
    public R save(@RequestBody SortEntity sort){
		sortService.save(sort);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SortEntity sort){
		sortService.updateById(sort);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] stIds){
		sortService.removeByIds(Arrays.asList(stIds));

        return R.ok();
    }

}
