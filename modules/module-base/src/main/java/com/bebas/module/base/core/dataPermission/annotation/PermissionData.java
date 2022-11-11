package com.bebas.module.base.core.dataPermission.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 *
 * @author wuhao
 * @date 2022/8/29 17:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionData {
}
