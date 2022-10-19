package com.bebas.org.modules.model.base.vo.permission;

import com.bebas.org.modules.model.base.model.SysPermissionModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wuhao
 * @date 2022/9/29 10:48
 */
@Data
public class AllocationModuleParamVO {

    @NotNull(message = "上级目录不能为空")
    private Long parentId;

    @NotNull(message = "没有可分配的路由列表")
    private List<SysPermissionModel> permissionModelList;

}
