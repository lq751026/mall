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

import com.shoping.mallgn.dao.AddressDao;
import com.shoping.mallgn.entity.AddressEntity;
import com.shoping.mallgn.service.AddressService;


@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressDao, AddressEntity> implements AddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object key = params.get("key");
        QueryWrapper<AddressEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(key.toString())){
            wrapper.like("as_name",key).or().like("as_telephone",key);
        }
        IPage<AddressEntity> page = this.page(
                new Query<AddressEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "address",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "address",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }

}