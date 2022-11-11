package com.bebas.module.generate.model.gen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 表数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表描述
     */
    private String comments;
    /**
     * 类名 类 (SysUser)
     */
    private String className;
    /**
     * 类名 属性 (sysUser)
     */
    private String classAttributeName;
    /**
     * 类名 标识 (sysuser)
     */
    private String classNameFlag;
    /**
     * 列信息
     */
    private List<ColumnEntity> columns;

}
