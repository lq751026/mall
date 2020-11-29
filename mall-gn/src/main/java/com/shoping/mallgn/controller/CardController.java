package com.shoping.mallgn.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cardService.queryPage(params);

        return R.ok().put("page", page);
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
    @RequestMapping("/save")
    public R save(@RequestBody CardEntity card){
		cardService.save(card);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CardEntity card){
		cardService.updateById(card);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] cdUuids){
		cardService.removeByIds(Arrays.asList(cdUuids));

        return R.ok();
    }

}
