package com.shoping.mallgn.controller;

import java.io.*;
import java.util.*;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.nacos.client.naming.utils.IoUtils;
import com.shoping.mallgn.utile.ExelcUtile;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.catalina.User;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoping.mallgn.entity.AddressEntity;
import com.shoping.mallgn.service.AddressService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author lq
 * @email 
 * @date 2020-11-26 14:42:28
 */
@RestController
@RequestMapping("/mallgn/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    /**
     * 列表
     *     集合进行缓存
     */

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        String page1 = (String) params.get("page");
        R r =null;
        if(page1.equals("1")){
            r = addressService.listpage(params);
        }else{
            r= addressService.listNoPage(params);
        }
        return r;
    }


    /**
     * 信息
     *
     */
    @RequestMapping("/info/{asId}")
    public R info(@PathVariable("asId") Integer asId){
		AddressEntity address = addressService.getById(asId);
        return R.ok().put("address", address);
    }

    /**
     * 保存
     * CacheEvict 对缓存进行删除
     */
    @CacheEvict(value = "address",key = "'R'")
    @RequestMapping("/save")
    public R save(@RequestBody AddressEntity address){
		addressService.save(address);
        return R.ok();
    }

    /**
     * 修改
     */
    @CacheEvict(value = "address",key = "'R'")
    @RequestMapping("/update")
    public R update(@RequestBody AddressEntity address){
		addressService.updateById(address);
        return R.ok();
    }

    /**
     * 删除
     */
    @CacheEvict(value = "address",key = "'R'")
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] asIds){
		addressService.removeByIds(Arrays.asList(asIds));
        return R.ok();
    }
    List<AddressEntity> list=null;
    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody  Integer[] asIds) throws ServletException, IOException {
        list =addressService.listByIds(Arrays.asList(asIds));
    }


    @RequestMapping("/exportAlls")
    public void export(HttpServletResponse response,HttpServletRequest request){
// 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        HashMap<String, String> map = new HashMap<>();
        map.put("asId","id");
        map.put("asName","姓名");
        map.put("asTelephone","电话号码");
        map.put("asAddress","地址");
        writer.setHeaderAlias(map);
//自定义标题别名
// 合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "地址管理");
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
//out为OutputStream，需要写出到的目标流
//response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String s = ExelcUtile.toUtf8String("地址管理");
        response.setHeader("Content-Disposition", "attachment;filename=" +s+ ".xls");
        ServletOutputStream out= null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
// 关闭writer，释放内存
            writer.close();
        }
//此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

}
