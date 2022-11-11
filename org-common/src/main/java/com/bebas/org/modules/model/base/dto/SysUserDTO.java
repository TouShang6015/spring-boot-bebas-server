package com.bebas.org.modules.model.base.dto;

import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户信息表 Dto
 *
 * @author WuHao
 * @company Wuhao
 * @date 2022-05-22 19:35:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends SysUserModel {

    private String deptName;

    private String leader;

    private Long roleId;

    private SysDeptModel dept;

    private List<SysRoleModel> roleList;

    private List<Long> roleIds;

    private List<SysPostModel> postList;

    private List<Long> postIds;

}
