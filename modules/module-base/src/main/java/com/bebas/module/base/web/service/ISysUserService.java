package com.bebas.module.base.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.model.SysUserModel;
import com.bebas.org.modules.model.base.vo.user.UserInfo;
import com.org.bebasWh.mapper.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * 用户信息表 业务接口
 *
 * @author WuHao
 * @date 2022-05-22 19:35:58
 */
public interface ISysUserService extends IService<SysUserModel> {

    /**
     * 获取当前登录人的信息
     *
     * @return
     */
    UserInfo selectUserInfo(Long id);

    /**
     * 查询用户详细列表
     *
     * @param param
     * @return
     */
    List<SysUserDTO> selectDetailList(SysUserDTO param);

    /**
     * 通过id获取用户详细信息
     *
     * @param id
     * @return
     */
    SysUserDTO selectUserDetailById(Long id);

    IPage<SysUserDTO> selectAllocatedList(IPage<SysUserDTO> page, SysUserDTO user);

    /**
     * 根据条件分页查询未分配用户角色列表-分页
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    IPage<SysUserDTO> selectUnallocatedList(IPage<SysUserDTO> page, SysUserDTO user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserModel selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUserDTO selectUserById(Long userId);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    String selectUserRoleGroup(String userName);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    String selectUserPostGroup(String userName);

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    boolean checkUserNameUnique(SysUserModel param);

    /**
     * 校验手机号码是否唯一
     *
     * @param param 用户信息
     * @return 结果
     */
    boolean checkPhoneUnique(SysUserModel param);

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkEmailUnique(SysUserModel user);

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    void checkUserAllowed(SysUserModel user);

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    void checkUserDataScope(Long userId);

    /**
     * 新增用户信息
     *
     * @param param 用户信息
     * @return 结果
     */
    int insertUser(SysUserDTO param);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUserDTO user);

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    boolean resetUserPwd(String userName, String password);

    /**
     * 导入用户数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    String importUser(List<SysUserModel> userList, Boolean isUpdateSupport, String operName);

    /**
     * 通过主键删除关联子表
     *
     * @param ids
     * @return
     */
    int deleteSublistByMainId(Collection<?> ids);

}
