package com.shoping.mallgn.service.impl;

import com.mysql.cj.util.StringUtils;
import io.renren.common.utils.R;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import com.shoping.mallgn.dao.CardDao;
import com.shoping.mallgn.entity.CardEntity;
import com.shoping.mallgn.service.CardService;


@Service("cardService")
public class CardServiceImpl extends ServiceImpl<CardDao, CardEntity> implements CardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<CardEntity> wrapper = new QueryWrapper<>();
        Object key = params.get("key");
        if(!StringUtils.isNullOrEmpty(key.toString())){
            wrapper.like("cd_amout",key);
        }
        IPage<CardEntity> page = this.page(
                new Query<CardEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "card",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "card",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }
}