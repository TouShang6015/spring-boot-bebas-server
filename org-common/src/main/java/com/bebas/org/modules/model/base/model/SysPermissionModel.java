package com.bebas.org.modules.model.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.org.bebasWh.core.model.TreeBaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 权限管理 Model
 *
 * @author WuHao
 * @date 2022-09-25 12:01:06
 * @tableName sys_permission
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("sys_permission")
public class SysPermissionModel extends TreeBaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id", dataType = "Long")
    private Long parentId;
    /**
     * 是否为路由
     */
    @ApiModelProperty(value = "是否为路由", dataType = "Integer")
    private Integer ifRoute;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", dataType = "String")
    private String title;
    /**
     * 接口路由
     */
    @ApiModelProperty(value = "接口路由", dataType = "String")
    private String routePath;
    /**
     * 原始路由
     */
    @ApiModelProperty(value = "原始路由", dataType = "String")
    private String originalRoutePath;
    /**
     * 接口标识
     */
    @ApiModelProperty(value = "接口标识", dataType = "String")
    private String permissionTag;
    /**
     * 请求方式 GET POST...
     */
    @ApiModelProperty(value = "请求方式 GET POST...", dataType = "String")
    private String requestMethod;
    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称", dataType = "String")
    private String moduleName;
    /**
     * 模块控制器对象
     */
    @ApiModelProperty(value = "模块控制器对象", dataType = "String")
    private String moduleController;
    /**
     * 接口访问规则
     * 1 授权访问
     * 2 匿名访问（不携带token）
     * 3 完全放行
     * 4 不可访问
     */
    @ApiModelProperty(value = "接口访问规则", dataType = "Integer")
    private Integer routeVisitRule;

}
