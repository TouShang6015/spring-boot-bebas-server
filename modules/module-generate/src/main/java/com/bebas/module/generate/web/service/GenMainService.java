package com.bebas.module.generate.web.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.bebas.module.generate.config.GeneratorConfig;
import com.bebas.module.generate.config.TemplateEnum;
import com.bebas.module.generate.model.GenerateModel;
import com.bebas.module.generate.model.TableColumnsModel;
import com.bebas.module.generate.model.TableInfoModel;
import com.bebas.module.generate.model.gen.ColumnEntity;
import com.bebas.module.generate.model.gen.TableEntity;
import com.org.bebasWh.exception.BusinessException;
import com.org.bebasWh.mapper.utils.ModelUtil;
import com.org.bebasWh.utils.DateUtils;
import com.org.bebasWh.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author wuhao
 * @date 2022/10/8 17:00
 */
@Service
public class GenMainService {

    @Resource
    private GeneratorConfig generatorConfig;

    /**
     * 表名转换为类名
     *
     * @param tableName
     * @param tablePrefixList
     * @return
     */
    public static String tableToClassName(String tableName, List<String> tablePrefixList) {
        if (CollUtil.isNotEmpty(tablePrefixList)) {
            for (String tablePrefix : tablePrefixList) {
                if (tableName.startsWith(tablePrefix)) {
                    tableName = tableName.replaceFirst(tablePrefix, "");
                }
            }
        }
        return columnToAttribute(tableName);
    }

    /**
     * 列名转换为属性名
     *
     * @param column
     * @return
     */
    private static String columnToAttribute(String column) {
        String value = ModelUtil.lineToHump(column);
        String first = value.substring(0, 1);
        return first.toUpperCase(Locale.ROOT) + value.substring(1);
    }

    /**
     * 代码生成
     *
     * @param tableInfoModels
     * @param tableColumnsModels
     * @param param
     * @param zip
     */
    public List<Map<String, Object>> generator(List<TableInfoModel> tableInfoModels, List<TableColumnsModel> tableColumnsModels, GenerateModel param, ZipOutputStream zip) {
        Optional.ofNullable(param.getTableNames()).ifPresent(tableNames -> {
            tableNames.forEach(tableName -> {
                TableInfoModel tableInfoModel = tableInfoModels.stream().filter(item -> item.getTableName().equals(tableName)).findFirst().orElse(null);
                if (Objects.nonNull(tableInfoModel)) {
                    List<TableColumnsModel> columnsModelList = tableColumnsModels.stream().filter(item -> item.getTableName().equals(tableName)).collect(Collectors.toList());
                    this.generator(tableInfoModel, columnsModelList, param, zip);
                }
            });
        });
        return null;
    }

    /**
     * 代码生成
     *
     * @param tableInfo
     * @param columnsList
     * @param param
     * @param zip
     */
    public Map<String, Object> generator(TableInfoModel tableInfo, List<TableColumnsModel> columnsList, GenerateModel param, ZipOutputStream zip) {
        // 表名
        String tableName = tableInfo.getTableName();
        // 需要过滤的后缀
        List<String> tablePrefix = param.getTablePrefix();
        // 类名
        String className = tableToClassName(tableName, tablePrefix);

        // 表信息
        TableEntity _tableEntity = TableEntity.builder()
                .tableName(tableName)
                .comments(tableInfo.getTableComment())
                .className(className)
                .classAttributeName(StringUtils.uncapitalize(className))
                .classNameFlag(className.toLowerCase(Locale.ROOT))
                .build();
        // 列信息
        List<ColumnEntity> _columnEntityList = new ArrayList<>();
        for (TableColumnsModel columnInfo : columnsList) {
            // 列名
            String columnName = columnInfo.getColumnName();
            String attrName = columnToAttribute(columnName);
            String attrType = generatorConfig.getType().get(columnInfo.getDataType());
            ColumnEntity columnEntity = ColumnEntity.builder()
                    .columnName(columnName)
                    .dataType(columnInfo.getDataType())
                    .comments(columnInfo.getColumnComment())
                    .extra(columnInfo.getExtra())
                    .attrName(attrName)
                    .attrNameAttribute(StringUtils.uncapitalize(attrName))
                    .attrType(attrType)
                    .build();
            if (!param.isHasBigDecimal() && attrType.equals("BigDecimal")) {
                param.setHasBigDecimal(true);
            }
            if (!param.isHasList() && "array".equals(columnEntity.getExtra())) {
                param.setHasList(true);
            }
            _columnEntityList.add(columnEntity);
        }
        _tableEntity.setColumns(_columnEntityList);
        Map<String, Object> map = this.handleTableInfoToMap(_tableEntity, param);
        this.handleDataZip(zip, map);
        return map;

    }

    /**
     * 数据处理转换为zip
     *
     * @param zip
     * @param map
     */
    private void handleDataZip(ZipOutputStream zip, Map<String, Object> map) {
        VelocityContext context = new VelocityContext(map);
        TemplateEnum[] templateBaseEnums = TemplateEnum.values();
        for (TemplateEnum templateBaseEnum : templateBaseEnums) {
            String template = templateBaseEnum.getTemplateName();
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                //添加到zip
                String fileName = getFileName(template, map);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zip.putNextEntry(zipEntry);
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new BusinessException("数据处理转换为zip，代码生成失败");
            }
        }
    }

    private String getFileName(String template, Map<String, Object> map) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        String arg1 = packagePath + map.get("packageModuleName");
        String arg2 = (String) map.get("moduleName");
        String arg3 = (String) map.get("className");
        for (TemplateEnum value : TemplateEnum.values()) {
            if (template.equals(value.getTemplateName())) {
                return StringUtils.format(value.getTemplatePath(), arg1, arg2, arg3);
            }
        }
        return null;
    }

    private Map<String, Object> handleTableInfoToMap(TableEntity tableEntity, GenerateModel param) {
        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        String packageModuleName = param.getPackageModuleName();
        if (StrUtil.isEmpty(packageModuleName)) {
            throw new BusinessException("packageModuleName is not null");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("className", tableEntity.getClassName());
        map.put("classAttributeName", tableEntity.getClassAttributeName());
        map.put("classNameFlag", tableEntity.getClassNameFlag());
        map.put("columns", tableEntity.getColumns());
        map.put("hasBigDecimal", param.isHasBigDecimal());
        map.put("hasList", param.isHasList());
        map.put("packageModuleName", param.getPackageModuleName());
        map.put("author", param.getAuthor());
        map.put("datetime", DateUtils.getTime());
        map.put("moduleName", param.getModuleName());
        map.put("ModuleName", param.getModuleName().toUpperCase(Locale.ROOT));
        return map;
    }


}
