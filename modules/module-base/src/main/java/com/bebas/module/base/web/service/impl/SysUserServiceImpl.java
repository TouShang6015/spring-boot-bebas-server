package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.module.base.core.dataPermission.annotation.PermissionData;
import com.bebas.module.base.mapper.SysUserMapper;
import com.bebas.module.base.web.service.*;
import com.bebas.org.common.constants.MessageCode;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.security.utils.SecurityUtils;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.modules.convert.base.SysUserConvert;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.model.*;
import com.bebas.org.modules.model.base.vo.user.UserInfo;
import com.bebas.org.modules.webapi.base.ISysPermissionWebApi;
import com.bebas.org.modules.webapi.base.ISysUserWebApi;
import com.org.bebasWh.exception.UserException;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户信息表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-24 23:43:38
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserModel> implements ISysUserService, ISysUserWebApi {

    @Resource
    private ISysDeptService sysDeptService;
    @Resource
    private ISysUserRoleService sysUserRoleService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysUserPostService sysUserPostService;
    @Resource
    private ISysPermissionWebApi sysPermissionWebApi;

    @Override
    @PermissionData
    public List<SysUserModel> listByParam(SysUserModel param) {
        this.buildParam(param);
        return super.listByParam(param);
    }


    /**
     * 查询列表并且分页通过model
     *
     * @param page
     * @param param
     * @return
     */
    @Override
    @PermissionData
    public IPage<SysUserModel> listPageByParam(IPage<SysUserModel> page, SysUserModel param) {
        this.buildParam(param);
        return super.listPageByParam(page, param);
    }

    private void buildParam(SysUserModel param) {
        // 部门查询条件
        Long deptId = param.getDeptId();
        if (Objects.nonNull(deptId)) {
            List<Long> deptIds = OptionalUtil.ofNullList(
                            sysDeptService.lambdaQuery().and(deptWrapper -> deptWrapper.eq(SysDeptModel::getId, deptId).or().apply(StringUtils.format("FIND_IN_SET({},{})", deptId, "ancestors"))).list()
                    )
                    .stream()
                    .map(SysDeptModel::getId)
                    .distinct()
                    .collect(Collectors.toList());
            if (CollUtil.isNotEmpty(deptIds)) {
                param.queryParamIn(SysUserModel::getDeptId, deptIds);
            }
        }
    }

    /**
     * 获取当前登录人的信息
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo selectUserInfo(Long id) {
        if (ObjectUtil.isNull(id))
            id = SecurityUtils.getUserId();
        UserInfo userInfo = new UserInfo();
        SysUserModel user = this.getById(id);
        userInfo.setUser(user);
        userInfo.setDept(sysDeptService.getById(user.getDeptId()));
        // 角色权限
        userInfo.setRoleList(sysRoleService.selectListByUserId(id));
        // 菜单权限
        userInfo.setPermissions(SecurityUtils.getLoginUser().getPermissions());
        return userInfo;
    }

    /**
     * 查询用户详细列表
     *
     * @param param
     * @return
     */
    @Override
    public List<SysUserDTO> selectDetailList(SysUserDTO param) {
        List<SysUserModel> userModelList = super.listByParam(param);
        if (CollUtil.isEmpty(userModelList)) {
            return CollUtil.newArrayList();
        }
        // 获取部门信息
        List<Long> deptIds = OptionalUtil.ofNullListDefault(userModelList.stream().map(SysUserModel::getDeptId).distinct().collect(Collectors.toList()), -1L);
        List<SysDeptModel> deptList = sysDeptService.lambdaQuery().in(SysDeptModel::getId, deptIds).list();

        List<SysUserDTO> dtoList = SysUserConvert.INSTANCE.convertToDTO(userModelList);
        dtoList.forEach(item -> {
            item.setDept(OptionalUtil.ofNullList(deptList).stream().filter(x -> x.getId().equals(item.getDeptId())).findFirst().orElse(null));
        });
        return dtoList;
    }

    /**
     * 通过id获取用户详细信息
     *
     * @param id
     * @return
     */
    @Override
    public SysUserDTO selectUserDetailById(Long id) {
        return baseMapper.selectUserDetailById(id);
    }

    @Override
    public IPage<SysUserDTO> selectAllocatedList(IPage<SysUserDTO> page, SysUserDTO user) {
        return baseMapper.selectAllocatedList(page, user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表-分页
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public IPage<SysUserDTO> selectUnallocatedList(IPage<SysUserDTO> page, SysUserDTO user) {
        return baseMapper.selectUnallocatedList(page, user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUserModel selectUserByUserName(String userName) {
        List<SysUserModel> sysUserModels = lambdaQuery().eq(SysUserModel::getUserName, userName).list();
        return OptionalUtil.ofNullList(sysUserModels).stream().findFirst().orElse(null);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUserDTO selectUserById(Long userId) {
        return baseMapper.selectUserDetailById(userId);
    }

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName) {
        List<SysRoleModel> list = sysUserRoleService.selectRolesByUserName(userName);
        if (CollectionUtils.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        return list.stream().map(SysRoleModel::getRoleName).collect(Collectors.joining(StringPool.COMMA));
    }

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName) {
        List<SysPostModel> postList = baseMapper.selectPostsByUserName(userName);
        if (CollectionUtils.isEmpty(postList)) {
            return StringUtils.EMPTY;
        }
        return postList.stream().map(SysPostModel::getPostName).collect(Collectors.joining(StringPool.COMMA));
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param param 用户名称
     * @return 结果 true 唯一 false 不唯一
     */
    @Override
    public boolean checkUserNameUnique(SysUserModel param) {
        Long userId = Objects.isNull(param.getId()) ? -1L : param.getId();
        SysUserModel model = lambdaQuery().eq(SysUserModel::getUserName, param.getUserName()).ne(SysUserModel::getId, userId).one();
        return Objects.isNull(model);
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param param 用户信息
     * @return 结果
     */
    @Override
    public boolean checkPhoneUnique(SysUserModel param) {
        Long userId = Objects.isNull(param.getId()) ? -1L : param.getId();
        SysUserModel model = lambdaQuery().eq(SysUserModel::getPhonenumber, param.getPhonenumber()).ne(SysUserModel::getId, userId).one();
        return Objects.isNull(model);
    }

    /**
     * 校验email是否唯一
     *
     * @param param 用户信息
     * @return 结果
     */
    @Override
    public boolean checkEmailUnique(SysUserModel param) {
        Long userId = Objects.isNull(param.getId()) ? -1L : param.getId();
        SysUserModel model = lambdaQuery().eq(SysUserModel::getEmail, param.getEmail()).ne(SysUserModel::getId, userId).one();
        return Objects.isNull(model);
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUserModel user) {
        if (Objects.nonNull(user) && SecurityUtils.isAdmin(user.getId())) {
            throw new UserException(MessageUtils.message(MessageCode.User.NOT_HANDLE_ADMIN_USER));
        }
    }

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    @Override
    public void checkUserDataScope(Long userId) {

    }

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUserDTO user) {
        SysUserModel insertParam = SysUserConvert.INSTANCE.convertToModel(user);
        insertParam.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        if (super.save(insertParam)) {
            Optional.ofNullable(user.getPostIds()).ifPresent(postIds -> {
                List<SysUserPostModel> insertPost = postIds.parallelStream().map(item -> SysUserPostModel.builder().userId(insertParam.getId()).postId(item).build()).collect(Collectors.toList());
                Optional.of(insertPost).ifPresent(sysUserPostService::insertBatch);
            });
            Optional.ofNullable(user.getRoleIds()).ifPresent(roleIds -> {
                List<SysUserRoleModel> insertRole = roleIds.parallelStream().map(item -> SysUserRoleModel.builder().userId(insertParam.getId()).roleId(item).build()).collect(Collectors.toList());
                Optional.of(insertRole).ifPresent(sysUserRoleService::insertBatch);
            });
        }
        return 1;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUserDTO user) {
        SysUserModel updateParam = SysUserConvert.INSTANCE.convertToModel(user);
        Long userId = user.getId();
        // 删除子表
        this.deleteSublistByMainId(CollUtil.newArrayList(userId));
        // 新增子表
        Optional.ofNullable(user.getPostIds()).ifPresent(ids -> {
            List<SysUserPostModel> insert = ids.parallelStream().map(item -> SysUserPostModel.builder().userId(userId).postId(item).build()).collect(Collectors.toList());
            Optional.of(insert).ifPresent(sysUserPostService::insertBatch);
        });
        Optional.ofNullable(user.getRoleIds()).ifPresent(ids -> {
            List<SysUserRoleModel> insert = ids.parallelStream().map(item -> SysUserRoleModel.builder().userId(userId).roleId(item).build()).collect(Collectors.toList());
            Optional.of(insert).ifPresent(sysUserRoleService::insertBatch);
        });
        super.updateById(updateParam);
        return 1;
    }

    /**
     * @param list
     * @return
     */
    @Override
    public boolean removeByIds(Collection<?> list) {
        // 删除子表
        this.deleteSublistByMainId(list);
        // 删除主表
        return super.removeByIds(list);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public boolean resetUserPwd(String userName, String password) {
        String newPassword = SecurityUtils.encryptPassword(password);
        return lambdaUpdate().set(SysUserModel::getPassword, newPassword).eq(SysUserModel::getUserName, userName).update();
    }

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUserModel> userList, Boolean isUpdateSupport, String operName) {
        return null;
    }

    /**
     * 通过主键删除关联子表
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteSublistByMainId(Collection<?> ids) {
        // 删除角色中间表
        sysUserRoleService.lambdaUpdate().in(SysUserRoleModel::getUserId, ids).remove();
        // 删除岗位中间表
        sysUserPostService.lambdaUpdate().in(SysUserPostModel::getUserId, ids).remove();
        return 1;
    }

    // =================================

}
