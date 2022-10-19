package com.bebas.org.modules.model.base.dto;

import com.bebas.org.modules.model.base.model.SysRoleModel;
import lombok.*;

import java.util.List;

/**
 * 角色信息表 Dto
 *
 * @author WuHao
 * @company Wuhao
 * @date 2022-05-25 19:02:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDTO extends SysRoleModel {

    private List<Long> menuIds;

    private List<Long> deptIds;

    private List<Long> permissionIds;

}
