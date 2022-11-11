package com.bebas.module.generate.web.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.module.generate.mapper.GeneratorMapper;
import com.bebas.module.generate.model.GenerateModel;
import com.bebas.module.generate.model.TableColumnsModel;
import com.bebas.module.generate.model.TableInfoModel;
import com.bebas.module.generate.model.TableQueryModel;
import com.org.bebasWh.exception.BusinessException;
import com.org.bebasWh.utils.page.PageUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorService {

    @Resource
    private GeneratorMapper mapper;
    @Resource
    private GenMainService genMainService;

    public IPage<TableInfoModel> queryList(TableQueryModel param) {
        return mapper.selectTableListPage(PageUtil.pageBean(param, TableInfoModel.class), param);
    }

    public byte[] generatorCode(GenerateModel param) {
        List<String> tableNames = CollUtil.isEmpty(param.getTableNames()) ? CollUtil.newArrayList("-1") : param.getTableNames();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 获取表信息
        TableQueryModel _queryParam = new TableQueryModel();
        _queryParam.setTableNames(tableNames);
        List<TableInfoModel> tableInfoList = mapper.selectTableList(_queryParam);
        if (CollectionUtils.isEmpty(tableInfoList)) {
            throw new BusinessException("获取表数据异常！");
        }
        // 获取列信息
        List<TableColumnsModel> tableColumnsModels = mapper.selectColumnsList(tableNames);
        // 生成代码
        genMainService.generator(tableInfoList, tableColumnsModels, param, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
