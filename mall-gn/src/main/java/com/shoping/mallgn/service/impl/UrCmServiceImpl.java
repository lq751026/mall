package com.shoping.mallgn.service.impl;

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
        IPage<UrCmEntity> page = this.page(
                new Query<UrCmEntity>().getPage(params),
                new QueryWrapper<UrCmEntity>()
        );

        return new PageUtils(page);
    }

}