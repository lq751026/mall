package com.shoping.mallgn.service.impl;

import com.mysql.cj.util.StringUtils;
import com.shoping.mallgn.entity.OrderEntity;
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

import com.shoping.mallgn.dao.MerchantDao;
import com.shoping.mallgn.entity.MerchantEntity;
import com.shoping.mallgn.service.MerchantService;


@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantDao, MerchantEntity> implements MerchantService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<MerchantEntity> wrapper = new QueryWrapper<>();
        Object key = params.get("key");
        if(!StringUtils.isNullOrEmpty(key.toString())){
            wrapper.like("mt_name",key);
        }
        IPage<MerchantEntity> page = this.page(
                new Query<MerchantEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "merchant",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "merchant",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }
}