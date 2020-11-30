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

import com.shoping.mallgn.entity.CardEntity;
import com.shoping.mallgn.service.CardService;
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
@RequestMapping("/mallgn/card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 列表
     *    @Cacheable(value = "card",key = "'R'")
     *     @CacheEvict(value = "card",key = "'R'")
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
            r = cardService.listpage(params);
        }else{
            r= cardService.listNoPage(params);
        }
        return r;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{cdUuid}")
    public R info(@PathVariable("cdUuid") String cdUuid){
		CardEntity card = cardService.getById(cdUuid);

        return R.ok().put("card", card);
    }

    /**
     * 保存
     */
    @CacheEvict(value = "card",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody CardEntity card){
		cardService.save(card);

        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "card",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody CardEntity card){
		cardService.updateById(card);

        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "card",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] cdUuids){
		cardService.removeByIds(Arrays.asList(cdUuids));

        return R.ok();
    }

}
