package com.shoping.mallgn.utile;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.StringUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExelcUtile{


    public static void getExelcUtile(HttpServletResponse response, List<Object> list,String fileName, Map<String,String>map){
        ExcelWriter writer = ExcelUtil.getWriter();
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
        String s = toUtf8String(fileName);
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



    public static String toUtf8String(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        label0:
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(c >= 0 && c <= '\377')
            {
                stringbuffer.append(c);
                continue;
            }
            byte abyte0[];
            try
            {
                abyte0 = Character.toString(c).getBytes("utf-8");
            }
            catch(Exception exception)
            {
                System.out.println(exception);
                abyte0 = new byte[0];
            }
            int j = 0;
            do
            {
                if(j >= abyte0.length)
                {
                    continue label0;
                }
                int k = abyte0[j];
                if(k < 0)
                {
                    k += 256;
                }
                stringbuffer.append("%" + Integer.toHexString(k).toUpperCase());
                j++;
            } while(true);
        }

        return stringbuffer.toString();
    }


}
