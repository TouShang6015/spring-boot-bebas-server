package com.bebas.module.generate.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.module.generate.model.GenerateModel;
import com.bebas.module.generate.model.TableInfoModel;
import com.bebas.module.generate.model.TableQueryModel;
import com.bebas.module.generate.web.service.GeneratorService;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 代码生成 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.GENERATE)
@Api(value = "Generate", tags = "代码生成")
public class GenerateController {

    @Autowired
    private GeneratorService generatorService;

    @ApiOperation(value = "queryList", httpMethod = "GET", response = Result.class)
    @GetMapping("/queryList")
    public Result queryList(TableQueryModel param) {
        IPage<TableInfoModel> tableInfoModelIPage = generatorService.queryList(param);
        return Result.success(tableInfoModelIPage);
    }

    /**
     * 生成代码
     */
    @PostMapping("/generateStart")
    public void code(@RequestBody GenerateModel param, HttpServletResponse response) throws IOException {
        byte[] data = generatorService.generatorCode(param);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"generatorCode.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}
