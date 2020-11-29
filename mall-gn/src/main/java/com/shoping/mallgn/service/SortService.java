package com.shoping.mallgn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import com.shoping.mallgn.entity.SortEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lq
 * @email 
 * @date 2020-11-26 14:42:28
 */
public interface SortService extends IService<SortEntity> {

    PageUtils queryPage(Map<String, Object> params);
    public List<SortEntity> listWithThree();
}

