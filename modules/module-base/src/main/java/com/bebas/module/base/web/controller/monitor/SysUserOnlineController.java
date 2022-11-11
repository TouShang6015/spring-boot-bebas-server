package com.bebas.module.base.web.controller.monitor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bebas.module.base.web.service.ISysUserTokenService;
import com.bebas.org.common.constants.SecurityConstant;
import com.bebas.org.common.web.controller.BaseController;
import com.bebas.org.framework.log.annotation.Log;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import com.bebas.org.modules.convert.base.SysUserTokenConvert;
import com.bebas.org.modules.model.base.dto.SysUserTokenDTO;
import com.bebas.org.modules.model.base.model.SysUserTokenModel;
import com.org.bebasWh.core.redis.RedisCache;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.bean.BeanUtil;
import com.org.bebasWh.utils.page.PageUtil;
import com.org.bebasWh.utils.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 在线用户监控
 *
 * @author wuhao
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/monitor/online")
@Api(tags = "在线用户监控")
public class SysUserOnlineController extends BaseController<ISysUserTokenService, SysUserTokenModel> {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected Result baseQueryPageByParam(@RequestBody SysUserTokenModel param) {
        IPage<SysUserTokenModel> result = service.listPageByParam(PageUtil.pageBean(param), param);
        IPage<SysUserTokenDTO> resultPage = new Page<>();
        BeanUtil.copyProperties(result, resultPage);
        List<SysUserTokenModel> records = result.getRecords();
        if (Objects.nonNull(records)) {
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
        List<Long> idList = StringUtils.splitToList(ids, Long::valueOf);
        List<SysUserTokenModel> list = service.lambdaQuery().in(SysUserTokenModel::getId, idList).list();
        OptionalUtil.ofNullList(list).forEach(item -> {
            if (service.removeById(item.getId())) {
                redisCache.deleteObject(SecurityConstant.LOGIN_TOKEN_KEY + item.getToken());
            }
        });
        return Result.success();
    }
}
