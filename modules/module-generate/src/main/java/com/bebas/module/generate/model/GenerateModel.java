package com.bebas.module.generate.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wuhao
 * @date 2022/9/2 11:19
 */
@Data
public class GenerateModel {

    /**
     * 基础包名 {eg: com.bebas }  该值为基础包名，在启动类Application的上一级，如com.bebas.StartApplication
     */
    @NotNull(message = "基础包名不能为空")
    private String packageModuleName;
    /**
     * 模块名 {eg: base  }  该值为模块名称，如base、quartz、generator
     */
    @NotNull(message = "模块名不能为空")
    private String moduleName;

    @NotNull(message = "需要生成的表名参数不能为空")
    private List<String> tableNames;

    /**
     * 表名前缀（如tb_test，该值为 tb_ ，会过滤掉前缀，生成TestModel、TestService等）
     */
    private List<String> tablePrefix;

    /**
     * 作者
     */
    @NotNull(message = "作者信息不能为空")
    private String author;

    private boolean hasBigDecimal = false;

    private boolean hasList = false;


}
