package com.bebas.org.modules.model.base.vo.user;

import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.dto.SysRoleDTO;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import lombok.Data;

import java.util.List;

/**
 * @author WuHao
 * @date 2022/6/4 17:14
 */
@Data
public class UserDetail {

    private SysUserDTO user;

    private SysDeptDTO dept;

    private List<SysRoleDTO> roleList;

}
