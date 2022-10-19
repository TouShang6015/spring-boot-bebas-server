package com.bebas.org.modules.model.base.vo.user;

import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author WuHao
 * @date 2022/6/4 17:19
 */
@Data
public class UserInfo {

    private SysUserModel user;

    private SysDeptModel dept;

    private List<SysRoleModel> roleList;

    private String token;
    /**
     * 权限标识
     */
    private Set<String> permissions;

}
