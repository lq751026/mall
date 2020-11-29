package com.shoping.mallgn;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.shoping.mallgn.entity.AddressEntity;
import com.shoping.mallgn.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MallGnApplicationTests {
	@Autowired
	private AddressService addressService;
	@Autowired
	private HttpServletResponse response;

	@Test
	void contextLoads() throws IOException {
		Integer ids[]={1,2};
		List<AddressEntity> rows = addressService.listByIds(Arrays.asList(ids));
		System.out.println(rows);
		ExcelWriter writer = ExcelUtil.getWriter();
// 一次性写出内容，使用默认样式，强制输出标题
		writer.write(rows, true);
//out为OutputStream，需要写出到的目标流

//response为HttpServletResponse对象
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
//test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
		response.setHeader("Content-Disposition","attachment;filename=test.xls");
		ServletOutputStream out=response.getOutputStream();

		writer.flush(out, true);
// 关闭writer，释放内存
		writer.close();
//此处记得关闭输出Servlet流
		IoUtil.close(out);
	}

}
