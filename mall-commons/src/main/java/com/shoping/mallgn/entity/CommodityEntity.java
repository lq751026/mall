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
@TableName("commodity")
public class CommodityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer cmId;
	/**
	 * 商品名
	 */
	private String cmName;
	/**
	 * 商品logo
	 */
	private String cmLogo;
	/**
	 * 商品的价格
	 */
	private Double cmPrice;
	/**
	 * 介绍表的id
	 */
	private Integer cmInId;
	/**
	 * 商品数量
	 */
	private Integer cmAmount;
	/**
	 * 商品已购数量
	 */
	private Integer cmAmounted;
	/**
	 * 每用户限购数量
	 */
	private Integer cmQuota;
	/**
	 * 商品分类的id
	 */
	private Integer stId;
	/**
	 * 商家id
	 */
	private Integer mtId;

}
