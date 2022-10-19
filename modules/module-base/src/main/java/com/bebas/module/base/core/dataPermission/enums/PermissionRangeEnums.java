package com.bebas.module.base.core.dataPermission.enums;

import lombok.Getter;

/**
 * 权限范围类型
 * @author Wuhao
 * @date 2022/8/28 22:33
 */
@Getter
public enum PermissionRangeEnums {

    ALL,        // 所有权限
    CUSTOM,     // 自定义
    DEPT_MY,    // 本部门
    DEPT,       // 本部门以及以下
    MY;         // 本人

}
