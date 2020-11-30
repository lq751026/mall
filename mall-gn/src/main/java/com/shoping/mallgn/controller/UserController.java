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

import com.shoping.mallgn.entity.UserEntity;
import com.shoping.mallgn.service.UserService;
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
@RequestMapping("/mallgn/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")||page1==null){
            r = userService.listpage(params);
        }else{
            r= userService.listNoPage(params);
        }
        return r;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{urId}")
    public R info(@PathVariable("urId") Integer urId){
		UserEntity user = userService.getById(urId);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "user",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "user",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "user",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] urIds){
		userService.removeByIds(Arrays.asList(urIds));

        return R.ok();
    }

}
