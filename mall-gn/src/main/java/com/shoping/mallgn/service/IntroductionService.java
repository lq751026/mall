package com.shoping.mallgn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import com.shoping.mallgn.entity.IntroductionEntity;
import io.renren.common.utils.R;

import java.util.Map;

/**
 * 
 *
 * @author lq
 * @email 
 * @date 2020-11-26 14:42:28
 */
public interface IntroductionService extends IService<IntroductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R listpage(Map<String, Object> params);

    R listNoPage(Map<String, Object> params);
}

