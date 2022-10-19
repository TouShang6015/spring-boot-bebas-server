package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import com.org.bebasWh.core.model.BaseModel;
import lombok.*;

/**
 * 素材管理 Model
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 * @tableName base_material_info
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("base_material_info")
public class BaseMaterialInfoModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 素材分类id
	 */
	@ApiModelProperty(value = "素材分类id", dataType = "Long")
	private Long materialTypeId;
	/**
	 * 素材名称
	 */
	@ApiModelProperty(value = "素材名称", dataType = "String")
	private String materialName;
	/**
	 * 相对路径链接url
	 */
	@ApiModelProperty(value = "相对路径链接url", dataType = "String")
	private String url;
	/**
	 * 素材链接url
	 */
	@ApiModelProperty(value = "素材链接url", dataType = "String")
	private String materialUrl;
	/**
	 * 素材缩略图url
	 */
	@ApiModelProperty(value = "素材缩略图url", dataType = "String")
	private String thumbnailUrl;
	/**
	 * 0正常 1停用
	 */
	@ApiModelProperty(value = "0正常 1停用", dataType = "String")
	private String status;

}
