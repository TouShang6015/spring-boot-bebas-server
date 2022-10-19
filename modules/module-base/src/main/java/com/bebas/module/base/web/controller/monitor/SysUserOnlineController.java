package com.bebas.module.base.web.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.bean.BeanUtil;
import com.org.bebasWh.utils.page.PageUtil;
import com.org.bebasWh.utils.result.Result;
import com.bebas.module.base.web.service.ISysUserTokenService;
import com.bebas.org.common.constants.SecurityConstant;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.convert.base.SysUserTokenConvert;
import com.bebas.org.modules.model.base.dto.SysUserTokenDTO;
import com.bebas.org.modules.model.base.model.SysUserTokenModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 在线用户监控
 *
 * @author wuhao
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/monitor/online")
@Api(tags = "在线用户监控")
public class SysUserOnlineController extends BaseController<ISysUserTokenService, SysUserTokenModel> {

    @Resource
    @Override
    protected void setService(ISysUserTokenService service) {
        super.service = service;
    }

    @Autowired
    private RedisCache redisCache;

    @Override
    protected Result baseQueryPageByParam(@RequestBody SysUserTokenModel param) {
        IPage<SysUserTokenModel> result = service.listPageByParam(PageUtil.pageBean(param), param);
        IPage<SysUserTokenDTO> resultPage = new Page<>();
        BeanUtil.copyProperties(result,resultPage);
        List<SysUserTokenModel> records = result.getRecords();
        if (Objects.nonNull(records)){
            List<SysUserTokenDTO> rows = SysUserTokenConvert.INSTANCE.convertToDTO(records);
            for (SysUserTokenDTO row : rows) {
                String token = row.getToken();
                String finalToken = SecurityConstant.LOGIN_TOKEN_KEY + token;
                row.setLoginUser(redisCache.getCacheObject(finalToken));
            }
            resultPage.setRecords(rows);
        }
        return Result.success(resultPage);
    }

    @Log(title = "强退用户")
    @Override
    protected Result baseDeleteByIds(@PathVariable("ids") String ids) {
        List<String> idList = Arrays.stream(ids.split(StringPool.COMMA)).collect(Collectors.toList());
        List<SysUserTokenModel> list = service.lambdaQuery().in(SysUserTokenModel::getId, idList).list();
        OptionalUtil.ofNullList(list).forEach(item -> {
            if (service.removeById(item.getId())) {
                redisCache.deleteObject(SecurityConstant.LOGIN_TOKEN_KEY + item.getToken());
            }
        });
        return Result.success();
    }
}
