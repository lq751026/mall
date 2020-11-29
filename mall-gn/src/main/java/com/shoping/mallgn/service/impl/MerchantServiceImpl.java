package com.shoping.mallgn.service.impl;

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
        IPage<MerchantEntity> page = this.page(
                new Query<MerchantEntity>().getPage(params),
                new QueryWrapper<MerchantEntity>()
        );

        return new PageUtils(page);
    }

}