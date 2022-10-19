package com.bebas.module.generate.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.module.generate.model.TableColumnsModel;
import com.bebas.module.generate.model.TableInfoModel;
import com.bebas.module.generate.model.TableQueryModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库接口
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2018-07-24
 */
public interface GeneratorMapper {

    List<TableInfoModel> selectTableList(TableQueryModel param);

    IPage<TableInfoModel> selectTableListPage(IPage<TableInfoModel> page,@Param("param") TableQueryModel param);

    List<TableColumnsModel> selectColumnsList(@Param("tableNames") List<String> tableNames);
}
