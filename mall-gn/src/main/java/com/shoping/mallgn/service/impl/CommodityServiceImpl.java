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

import com.shoping.mallgn.dao.CommodityDao;
import com.shoping.mallgn.entity.CommodityEntity;
import com.shoping.mallgn.service.CommodityService;


@Service("commodityService")
public class CommodityServiceImpl extends ServiceImpl<CommodityDao, CommodityEntity> implements CommodityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<CommodityEntity> wrapper = new QueryWrapper<>();
        Object key = params.get("key");
        if(!StringUtils.isNullOrEmpty(key.toString())){
             wrapper.like("cm_name",key);
        }
        IPage<CommodityEntity> page = this.page(
                new Query<CommodityEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "commodity",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "commodity",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }
}