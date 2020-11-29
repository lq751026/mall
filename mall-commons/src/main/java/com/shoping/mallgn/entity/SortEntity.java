package com.shoping.mallgn.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author lq
 * @email 
 * @date 2020-11-26 14:42:28
 */
@Data
@TableName("sort")
public class SortEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer stId;
	/**
	 * 分类名
	 */
	private String stName;
	/**
	 * 一级分类
	 */
	private Integer stFirstLevel;
	/**
	 * 二级分类
	 */
	private Integer stSecondLevel;

	@TableField(exist = false)  //不是数据库的
	@JsonInclude(JsonInclude.Include.NON_EMPTY)  //空数组就不返回
      //子类的集合
	private List<SortEntity> childs;

}
