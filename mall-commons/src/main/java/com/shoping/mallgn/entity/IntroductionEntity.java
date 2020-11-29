package com.shoping.mallgn.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author lq
 * @email 
 * @date 2020-11-26 14:42:28
 */
@Data
@TableName("introduction")
public class IntroductionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品得文字描述
	 */
	private String inText;
	/**
	 * 
	 */
	@TableId
	private Integer inId;
	/**
	 * 商品图片
	 */
	private String inImage;

}
