package com.shoping.mallgn.service.impl;

import com.mysql.cj.util.StringUtils;
import com.shoping.mallgn.entity.SortEntity;
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

import com.shoping.mallgn.dao.OrderDao;
import com.shoping.mallgn.entity.OrderEntity;
import com.shoping.mallgn.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<OrderEntity> wrapper = new QueryWrapper<>();
        Object key = params.get("key");
        if(!StringUtils.isNullOrEmpty(key.toString())){
            wrapper.like("ur_id",key);
        }
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
               wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "order",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "order",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }
}