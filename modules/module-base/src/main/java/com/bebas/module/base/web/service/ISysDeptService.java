package com.bebas.module.base.web.service;

import com.bebas.org.common.utils.tree.vo.TreeModel;
import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.org.bebasWh.mapper.service.IService;

import java.util.List;

/**
 * 部门表 业务接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface ISysDeptService extends IService<SysDeptModel> {

    /**
     * 获取详情根据id
     *
     * @param id
     * @return
     */
    SysDeptDTO selectDetailById(Long id);

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    List<SysDeptModel> selectDeptList(SysDeptModel dept);

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    List<SysDeptDTO> buildDeptTree(List<SysDeptModel> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    List<TreeModel> buildDeptTreeSelect(List<SysDeptModel> depts);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    SysDeptModel selectDeptById(Long deptId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    int selectNormalChildrenDeptById(Long deptId);

    /**
     * 是否存在部门子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    boolean hasChildByDeptId(List<Long> deptList);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    boolean checkDeptExistUser(List<Long> deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    boolean checkDeptNameUnique(SysDeptModel dept);

    /**
     * 校验部门是否有数据权限
     *
     * @param deptId 部门id
     */
    void checkDeptDataScope(Long deptId);

    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    boolean insertDept(SysDeptDTO dept);

    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    boolean updateDept(SysDeptModel dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    int deleteDeptById(Long deptId);

}
