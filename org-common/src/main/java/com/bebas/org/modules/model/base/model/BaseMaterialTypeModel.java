package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.org.bebasWh.core.model.TreeBaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 素材分类 Model
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 * @tableName base_material_type
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("base_material_type")
public class BaseMaterialTypeModel extends TreeBaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称", dataType = "String")
	private String typeName;
	/**
	 * 父节点id
	 */
	@ApiModelProperty(value = "父节点id", dataType = "Long")
	private Long parentId;
	/**
	 * 排序号
	 */
	@ApiModelProperty(value = "排序号", dataType = "Integer")
	private Integer sort;
	/**
	 * 祖级列表
	 */
	@ApiModelProperty(value = "祖级列表", dataType = "String")
	private String ancestors;
	/**
	 * 0正常 1停用
	 */
	@ApiModelProperty(value = "0正常 1停用", dataType = "String")
	private String status;

}
