package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import com.org.bebasWh.core.model.BaseModel;
import lombok.*;

/**
 * 角色和部门关联表 Model
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 * @tableName sys_role_dept
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_role_dept")
public class SysRoleDeptModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID", dataType = "Long")
	private Long roleId;
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID", dataType = "Long")
	private Long deptId;

}
