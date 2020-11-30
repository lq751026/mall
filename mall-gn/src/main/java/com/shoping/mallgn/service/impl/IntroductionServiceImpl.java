package com.shoping.mallgn.service.impl;

import com.mysql.cj.util.StringUtils;
import com.shoping.mallgn.entity.MerchantEntity;
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

import com.shoping.mallgn.dao.IntroductionDao;
import com.shoping.mallgn.entity.IntroductionEntity;
import com.shoping.mallgn.service.IntroductionService;


@Service("introductionService")
public class IntroductionServiceImpl extends ServiceImpl<IntroductionDao, IntroductionEntity> implements IntroductionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<IntroductionEntity> wrapper = new QueryWrapper<>();
        Object key = params.get("key");
        if(!StringUtils.isNullOrEmpty(key.toString())){
            wrapper.like("in_text",key);
        }
        IPage<IntroductionEntity> page = this.page(
                new Query<IntroductionEntity>().getPage(params),
              wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "introduction",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "introduction",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }
}