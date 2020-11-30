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
@TableName("orders")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	@TableId
	private String orUuid;
	/**
	 * 订单备注
	 */
	private String orText;
	/**
	 * 状态 （已支付1  未支付0  已取消-1  已完成2）
	 */
	private Integer orStatus;
	/**
	 * 商品状态(库存中,等待发货，已发货，已签收)
	 */
	private Integer orCmStatus;
	/**
	 * 购买人
	 */
	private Integer urId;
	/**
	 * 商品id
	 */
	private Integer cmId;

}
