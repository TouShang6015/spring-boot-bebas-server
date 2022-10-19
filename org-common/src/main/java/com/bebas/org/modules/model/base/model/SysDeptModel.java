package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.org.bebasWh.core.model.TreeBaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 部门表 Model
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 * @tableName sys_dept
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_dept")
public class SysDeptModel extends TreeBaseModel {

	public final static String DEFAULT_ANCESTORS = "0";

	private static final long serialVersionUID = 1L;

	/**
	 * 父部门id
	 */
	@ApiModelProperty(value = "父部门id", dataType = "Long")
	private Long parentId;
	/**
	 * 祖级列表
	 */
	@ApiModelProperty(value = "祖级列表", dataType = "String")
	private String ancestors;
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称", dataType = "String")
	private String deptName;
	/**
	 * 显示顺序
	 */
	@ApiModelProperty(value = "显示顺序", dataType = "Integer")
	private Integer orderNum;
	/**
	 * 负责人
	 */
	@ApiModelProperty(value = "负责人", dataType = "String")
	private String leader;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话", dataType = "String")
	private String phone;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱", dataType = "String")
	private String email;
	/**
	 * 部门状态（0正常 1停用）
	 */
	@ApiModelProperty(value = "部门状态（0正常 1停用）", dataType = "String")
	private String status;

}
