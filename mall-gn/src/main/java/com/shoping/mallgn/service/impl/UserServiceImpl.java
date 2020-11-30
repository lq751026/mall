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

import com.shoping.mallgn.dao.UserDao;
import com.shoping.mallgn.entity.UserEntity;
import com.shoping.mallgn.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object key = params.get("key");
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if(!StringUtils.isNullOrEmpty(key.toString())){
             wrapper.like("ur_name",key);
        }
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }
    @Cacheable(value = "user",key = "'R'")
    public R listpage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);

        return R.ok().put("page", page);
    }

    @CacheEvict(value = "user",key = "'R'")
    public R listNoPage(Map<String, Object> params){
        PageUtils page = this.queryPage(params);
        return R.ok().put("page", page);
    }
}