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
@TableName("merchant")
public class MerchantEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer mtId;
	/**
	 * 商家余额
	 */
	private Double mtBalance;
	/**
	 * 商家名
	 */
	private String mtName;
	/**
	 * 商家密码
	 */
	private String mtPassword;

}
