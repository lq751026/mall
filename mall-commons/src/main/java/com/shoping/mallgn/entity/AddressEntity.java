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
@TableName("address")
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 地址id
	 */
	@TableId
	private Integer asId;
	/**
	 * 签收人姓名
	 */
	private String asName;
	/**
	 * 电话
	 */
	private Long asTelephone;
	/**
	 * 地址
	 */
	private String asAddress;

}
