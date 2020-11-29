package com.shoping.mallgn.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import com.shoping.mallgn.dao.SortDao;
import com.shoping.mallgn.entity.SortEntity;
import com.shoping.mallgn.service.SortService;


@Service("sortService")
public class SortServiceImpl extends ServiceImpl<SortDao, SortEntity> implements SortService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SortEntity> page = this.page(
                new Query<SortEntity>().getPage(params),
                new QueryWrapper<SortEntity>()
        );

        return new PageUtils(page);
    }



    public List<SortEntity> listWithThree() {
        //1.查出所有分类
        List<SortEntity> list = baseMapper.selectList(null);
        //组成父子结构
        //找出一级分类
        List<SortEntity> collect = list.stream().filter((pmsCategoryEntity -> {
            return pmsCategoryEntity.getStFirstLevel() == 0;
        })).map(menu->{
            menu.setChilds(getChider(menu,list));
            return menu;
        }).collect(Collectors.toList());
        return collect;
    }


    //递归查找所有菜单的子菜单
    private List<SortEntity> getChider(SortEntity root,List<SortEntity> all){
        List<SortEntity> collect = all.stream().filter(categoryEntity -> {
            return categoryEntity.getStFirstLevel() == root.getStId();
        }).map(categoryEntity -> {
            //1.找到子菜单
            categoryEntity.setChilds(getChider(categoryEntity, all));
            return categoryEntity;
        }).collect(Collectors.toList());
        return collect;
    }
}