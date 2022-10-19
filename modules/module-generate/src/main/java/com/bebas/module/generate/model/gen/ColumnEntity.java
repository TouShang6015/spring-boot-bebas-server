package com.bebas.module.generate.model.gen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 列的属性
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColumnEntity {
	/**
	 * 列名
	 */
    private String columnName;
	/**
	 * 列类型
	 */
	private String dataType;
	/**
	 * 列描述
	 */
	private String comments;

	/**
	 * 列 名称 (UserName)
	 */
	private String attrName;
	/**
	 * 列 属性 (userName)
	 */
	private String attrNameAttribute;
	/**
	 * 属性类型
	 */
	private String attrType;
    //auto_increment
    private String extra;

}
