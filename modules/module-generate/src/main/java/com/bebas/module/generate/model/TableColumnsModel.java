package com.bebas.module.generate.model;

import lombok.Data;

/**
 * @author Wuhao
 * @date 2022/9/1 21:20
 */
@Data
public class TableColumnsModel {

    private String databaseName;
    private String tableName;
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;
}
