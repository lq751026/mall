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

	@Test
	void contextLoads() throws IOException {

	}

}
