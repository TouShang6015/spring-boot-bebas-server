package com.bebas.module.generate.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Wuhao
 * @date 2022/9/1 21:20
 */
@Data
public class TableInfoModel {

    private String databaseName;

    private String tableName;

    private String engine;

    private String tableComment;

    private Date createTime;

    private Date updateTime;

}
