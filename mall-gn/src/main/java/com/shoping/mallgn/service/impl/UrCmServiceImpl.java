package com.shoping.mallgn.service.impl;

import com.mysql.cj.util.StringUtils;
import com.shoping.mallgn.entity.UserAddressEntity;
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

import com.shoping.mallgn.dao.UrCmDao;
import com.shoping.mallgn.entity.UrCmEntity;
import com.shoping.mallgn.service.UrCmService;


@Service("urCmService")
public class UrCmServiceImpl extends ServiceImpl<UrCmDao, UrCmEntity> implements UrCmService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object key = params.get("key");
        QueryWrapper<UrCmEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(key.toString())){
            wrapper.like("ur_id",key);
        }
        IPage<UrCmEntity> page = this.page(
                new Query<UrCmEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "urcm",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "urcm",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = queryPage(params);
        return R.ok().put("page", page);
    }
}