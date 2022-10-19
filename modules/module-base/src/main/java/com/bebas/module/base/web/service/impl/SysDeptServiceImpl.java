package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.org.bebasWh.utils.OptionalUtil;
import com.bebas.module.base.core.dataPermission.annotation.PermissionData;
import com.bebas.module.base.mapper.SysDeptMapper;
import com.bebas.module.base.web.service.ISysRoleDeptService;
import com.bebas.module.base.web.service.ISysRoleService;
import com.bebas.module.base.web.service.ISysUserService;
import com.org.bebasWh.exception.BusinessException;
import com.org.bebasWh.utils.StringUtils;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.common.utils.tree.TreeService;
import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.convert.base.SysDeptConvert;
import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.module.base.web.service.ISysDeptService;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.bebas.org.modules.model.base.model.SysRoleDeptModel;
import com.bebas.org.modules.model.base.model.SysRoleModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门表 业务实现类
 *
 * @author WuHao
 * @date 2022-05-25 22:01:16
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptModel> implements ISysDeptService {

    @Resource
    @Override
    protected void setMapper(SysDeptMapper mapper) {
        super.mapper = mapper;
    }

    @Resource
    private ISysRoleDeptService sysRoleDeptService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private TreeService<SysDeptDTO> treeService;

    @Override
    @PermissionData
    public List<SysDeptModel> listByParam(SysDeptModel param) {
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
    public IPage<SysDeptModel> listPageByParam(IPage<SysDeptModel> page, SysDeptModel param) {
        return super.listPageByParam(page,param);
    }

    /**
     * 获取详情根据id
     *
     * @param id
     * @return
     */
    @Override
    public SysDeptDTO selectDetailById(Long id) {
        SysDeptDTO dto = (SysDeptDTO) this.getById(id);
        return dto;
    }

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<SysDeptModel> selectDeptList(SysDeptModel dept) {
        return this.listByParam(dept);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<SysDeptDTO> buildDeptTree(List<SysDeptModel> depts) {
        List<SysDeptDTO> convert = SysDeptConvert.INSTANCE.convertToDTO(depts);
        return treeService.convertTree(convert);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeModel> buildDeptTreeSelect(List<SysDeptModel> depts) {
        List<SysDeptDTO> convert = SysDeptConvert.INSTANCE.convertToDTO(depts);
        return convert.stream().map(TreeModel::new).collect(Collectors.toList());
    }

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Long> selectDeptListByRoleId(Long roleId) {
        SysRoleModel roleModel = sysRoleService.getById(roleId);
        if (Objects.isNull(roleModel))
            return CollUtil.newArrayList();
        List<SysRoleDeptModel> roleDeptModelList = sysRoleDeptService.lambdaQuery().eq(SysRoleDeptModel::getRoleId, roleId).list();
        if (CollUtil.isEmpty(roleDeptModelList)) {
            return CollUtil.newArrayList();
        }
        Integer deptCheckStrictly = Objects.isNull(roleModel.getDeptCheckStrictly()) ? Integer.valueOf(Constants.BOOLEAN.FALSE) : roleModel.getDeptCheckStrictly();
        if (Objects.equals(Constants.BOOLEAN.FALSE, deptCheckStrictly.toString())) {      // 不关联
            List<Long> deptIdList = roleDeptModelList.parallelStream().map(SysRoleDeptModel::getDeptId).distinct().collect(Collectors.toList());
            List<SysDeptModel> deptList = super.listByIds(deptIdList);
            if (CollUtil.isEmpty(deptList))
                return CollUtil.newArrayList();
            return CollUtil.newArrayList(
                    deptList.parallelStream().filter(item -> !deptIdList.contains(item.getId())).map(SysDeptModel::getParentId).findFirst().orElse(NumberUtils.LONG_ZERO)
            );
        } else {
            return roleDeptModelList.parallelStream().map(SysRoleDeptModel::getDeptId).distinct().collect(Collectors.toList());
        }
    }

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDeptModel selectDeptById(Long deptId) {
        return super.getById(deptId);
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(Long deptId) {
        return mapper.selectNormalChildrenDeptById(deptId);
    }

    /**
     * 是否存在部门子节点
     *
     * @param deptIdList 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(List<Long> deptIdList) {
        return lambdaQuery().eq(SysDeptModel::getDelFlag,Constants.DelFlag.NORMAL).in(SysDeptModel::getParentId,deptIdList).count() > 0;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptIdList 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(List<Long> deptIdList) {
        return sysUserService.lambdaQuery().eq(SysUserModel::getDelFlag,Constants.DelFlag.NORMAL).in(SysUserModel::getDeptId,deptIdList).count() > 0;
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果 true 唯一 false 不唯一
     */
    @Override
    public boolean checkDeptNameUnique(SysDeptModel dept) {
        Long deptId = Objects.isNull(dept.getId()) ? -1L : dept.getId();
        List<SysDeptModel> list = lambdaQuery()
                .eq(SysDeptModel::getDeptName, dept.getDeptName())
                .eq(SysDeptModel::getParentId, Objects.isNull(dept.getParentId()) ? 0 : dept.getParentId())
                .ne(SysDeptModel::getId, deptId)
                .list();
        return CollUtil.isEmpty(list);
    }

    /**
     * 校验部门是否有数据权限
     *
     * @param deptId 部门id
     */
    @Override
    public void checkDeptDataScope(Long deptId) {

    }

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean insertDept(SysDeptDTO dept) {
        SysDeptModel parentDept = super.getById(dept.getParentId());
        if (Objects.nonNull(parentDept)) {
            // 如果父节点不为正常状态,则不允许新增子节点
            if (Constants.Status.NO_NORMAL.equals(parentDept.getStatus())) {
                throw new BusinessException(MessageUtils.message("business.base.dept.status.no_normal"));
            }
            dept.setAncestors(parentDept.getAncestors() + StringPool.COMMA + dept.getParentId());
        }else{
            dept.setAncestors(SysDeptModel.DEFAULT_ANCESTORS);
        }
        return super.save(dept);
    }

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean updateDept(SysDeptModel dept) {
        SysDeptModel newParentDept = super.getById(dept.getParentId());
        SysDeptModel oldDept = super.getById(dept.getId());
        List<SysDeptModel> updateList = CollUtil.newArrayList();
        if (Objects.nonNull(newParentDept) && Objects.nonNull(oldDept)) {
            String newAncestors = newParentDept.getAncestors() + StringPool.COMMA + newParentDept.getId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateList.addAll(updateDeptChildren(dept.getId(), newAncestors, oldAncestors));
        }
        updateList.add(dept);
        boolean result = super.updateBatchById(updateList);
        if (Constants.Status.NORMAL.equals(dept.getStatus()) && StringUtils.isNotEmpty(dept.getAncestors()) && !StringUtils.equals(SysDeptModel.DEFAULT_ANCESTORS, dept.getAncestors())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(dept);
        }
        return result;
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId) {
        return 0;
    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public List<SysDeptModel> updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        List<SysDeptModel> list = OptionalUtil.ofNullList(mapper.selectChildrenDeptById(deptId));
        list.parallelStream().forEach(item -> {
            item.setAncestors(item.getAncestors().replaceFirst(oldAncestors, newAncestors));
        });
        return list;
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(SysDeptModel dept) {
        String ancestors = dept.getAncestors();
        List<Long> deptIds = Arrays.stream(ancestors.split(StringPool.COMMA)).map(Long::valueOf).collect(Collectors.toList());
        lambdaUpdate().set(SysDeptModel::getStatus,Constants.Status.NORMAL).in(SysDeptModel::getId,deptIds).update();
    }

}
