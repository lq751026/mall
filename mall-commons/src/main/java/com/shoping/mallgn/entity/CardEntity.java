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
@TableName("card")
public class CardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 卡密唯一id
	 */
	@TableId
	private String id;

	private String cdUuid;
	/**
	 * 卡密使用状态（0已使用 1未使用）
	 */
	private Integer cdStatus;



	/**
	 * 充值的金额
	 */
	private Double cdAmout;

}
