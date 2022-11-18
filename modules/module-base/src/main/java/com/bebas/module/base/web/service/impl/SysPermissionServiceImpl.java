package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.bebas.module.base.mapper.SysPermissionMapper;
import com.bebas.module.base.web.service.ISysPermissionService;
import com.bebas.module.base.web.service.ISysRolePermissionService;
import com.bebas.module.base.web.service.ISysUserRoleService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.SecurityConstant;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.enums.permission.VisitRuleEnum;
import com.bebas.org.common.security.service.SecurityService;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.utils.tree.TreeService;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.model.base.dto.SysPermissionDTO;
import com.bebas.org.modules.model.base.model.SysPermissionModel;
import com.bebas.org.modules.model.base.model.SysRolePermissionModel;
import com.bebas.org.modules.webapi.base.ISysPermissionWebApi;
import com.org.bebasWh.exception.CommonException;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.utils.OptionalUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 权限管理 业务实现类
 *
 * @author WuHao
 * @date 2022-09-25 12:01:06
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionModel> implements ISysPermissionService, ISysPermissionWebApi {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Resource
    private ISysRolePermissionService sysRolePermissionService;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private TreeService<SysPermissionDTO> treeService;
    @Autowired
    private HttpSecurity httpSecurity;

    @Override
    public List<SysPermissionModel> listByParam(SysPermissionModel param) {
        return this.getPermissionList(param);
    }

    /**
     * 非超级管理员查询列表
     *
     * @param param
     * @return
     */
    private List<SysPermissionModel> getPermissionList(SysPermissionModel param) {
        if (SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
            return super.listByParam(param);
        }
        Set<Long> roleIds = SecurityUtils.getLoginUser().getRoleIds();
        List<Long> permissionIds = OptionalUtil.ofNullList(
                sysRolePermissionService.lambdaQuery()
                        .in(SysRolePermissionModel::getRoleId, roleIds)
                        .select(SysRolePermissionModel::getPermissionId).list()
        ).stream().map(SysRolePermissionModel::getPermissionId).distinct().collect(Collectors.toList());
        List<Long> finalPermissionIds = OptionalUtil.ofNullListDefault(permissionIds, -1L);
        param.queryParamIn(SysPermissionModel::getId, finalPermissionIds);
        return super.listByParam(param);
    }

    /**
     * 获取项目路由地址
     *
     * @return
     */
    @Override
    public List<SysPermissionModel> getProjectRequestMapping() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        return handlerMethods.keySet().stream().map(requestMappingInfo -> {
            HandlerMethod handlerMethod = handlerMethods.get(requestMappingInfo);
            SysPermissionModel param = new SysPermissionModel();
            String moduleName = "";
            String title = "";
            String requestMethod = "";
            String routePath = "";

            Api annotation = handlerMethod.getBeanType().getAnnotation(Api.class);
            if (Objects.nonNull(annotation)) {
                moduleName = annotation.tags()[0];
            }
            for (RequestMethod method : requestMappingInfo.getMethodsCondition().getMethods()) {
                requestMethod = method.toString();
                break;
            }
            for (String pattern : requestMappingInfo.getPatternsCondition().getPatterns()) {
                routePath = pattern;
                break;
            }
            title = handlerMethod.getMethod().getName();

            param.setModuleController(handlerMethod.getBean().toString());
            param.setModuleName(moduleName);
            param.setTitle(title);
            param.setRequestMethod(requestMethod);
            param.setRoutePath(routePath);
            param.setOriginalRoutePath(routePath);
            param.setRouteVisitRule(VisitRuleEnum.AUTH.getValue());
            return param;
        }).collect(Collectors.toList());
    }

    /**
     * 接口路由同步
     *
     * @return
     */
    @Override
    public boolean handleMappingSync() {
        // 获取所有接口列表
        List<String> originalRoutePathList = OptionalUtil.ofNullList(
                super.lambdaQuery()
                        .select(SysPermissionModel::getOriginalRoutePath)
                        .eq(SysPermissionModel::getIfRoute, Constants.BOOLEAN.TRUE)
                        .list()
        ).stream().map(SysPermissionModel::getOriginalRoutePath).collect(Collectors.toList());

        List<SysPermissionModel> projectRequestMapping = this.getProjectRequestMapping();
        projectRequestMapping.parallelStream().forEach(item -> {
            item.setIfRoute(Integer.valueOf(Constants.BOOLEAN.TRUE));
            // routePath
            Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(item.getOriginalRoutePath());
            String routePath = item.getOriginalRoutePath();
            while (matcher.find()) {
                routePath = matcher.replaceFirst(StringPool.ASTERISK);
            }
            item.setRoutePath(routePath);
            // 路由标识
            String replaceStr = item.getRoutePath().replaceAll(StringPool.SLASH, StringPool.COLON);
            if (replaceStr.charAt(0) == ':') {
                replaceStr = replaceStr.substring(1);
            }
            item.setPermissionTag(replaceStr);
        });
        // 筛选需要新增的数据
        List<SysPermissionModel> INSERT_PARAM = projectRequestMapping.parallelStream().filter(item -> !originalRoutePathList.contains(item.getOriginalRoutePath())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(INSERT_PARAM)) {
            super.saveBatch(INSERT_PARAM);
            log.info("[路由同步] 同步成功：{}", JSON.toJSONString(INSERT_PARAM));
        }
        return true;
    }

    /**
     * 分配路由模块
     *
     * @param parentId
     * @param permissionModelList
     * @return
     */
    @Override
    public boolean handleAllocationRouteModule(Long parentId, List<SysPermissionModel> permissionModelList) {
        List<SysPermissionModel> UPDATE_PARAM = OptionalUtil.ofNullList(permissionModelList).stream()
                .peek(item -> item.setParentId(parentId))
                .collect(Collectors.toList());
        if (UPDATE_PARAM.size() < 1) {
            return false;
        }
        return super.updateBatchById(UPDATE_PARAM);
    }

    /**
     * 构建树结构列表
     *
     * @param dtoList
     * @return
     */
    @Override
    public List<TreeModel> buildTreePermissionList(List<SysPermissionDTO> dtoList) {
        if (CollUtil.isEmpty(dtoList))
            return CollUtil.newArrayList();
        return treeService.convertTree(dtoList).stream().map(TreeModel::new).collect(Collectors.toList());
    }

    /**
     * 获取角色的路由列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Long> rolePermissionByRoleId(Long roleId) {
        List<SysRolePermissionModel> resultList = sysRolePermissionService.lambdaQuery()
                .select(SysRolePermissionModel::getPermissionId)
                .eq(SysRolePermissionModel::getRoleId, roleId)
                .list();
        return OptionalUtil.ofNullList(resultList).parallelStream().map(SysRolePermissionModel::getPermissionId).distinct().collect(Collectors.toList());
    }

    /**
     * 刷新security动态权限
     */
    @Override
    public void flushPermissionConfig() {
        try {
            securityService.flushHttpSecurityConfig(httpSecurity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("刷新security配置失败!");
        }
    }

    /**
     * 获取所有控制器信息列表
     *
     * @return
     */
    @Override
    public List<SysPermissionModel> getRouteList() {
        return super.lambdaQuery().eq(SysPermissionModel::getIfRoute, Constants.BOOLEAN.TRUE).list();
    }

    /**
     * 获取用户的权限标识
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getUserPermissionTag(Long userId) {
        if (SecurityUtils.isAdmin(userId)) {
            return CollUtil.newHashSet(SecurityConstant.PERMISSION_TAG);
        }
        return OptionalUtil.ofNullList(baseMapper.selectListByUserId(userId)).stream().map(SysPermissionModel::getPermissionTag).collect(Collectors.toSet());
    }
}
