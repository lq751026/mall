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
@TableName("user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer urId;
	/**
	 * 用户余额;
	 */
	private Double urBalance;
	/**
	 * 用户名（登录账户）   唯一
	 */
	private String  urName;
	/**
	 * 电子邮箱（登录账户）
	 */
	private String urEmail;
	/**
	 * 用户名密码
	 */
	private String urPassword;
	/**
	 * 状态是否激活 （0没激活，1激活）
	 */
	private Integer urStatus;
	/**
	 * 激活码
	 */
	private String urUuid;

}
