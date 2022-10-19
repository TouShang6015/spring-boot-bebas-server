package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bebas.org.modules.model.base.dto.SysUserDTO;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.bebas.org.modules.model.base.model.SysPostModel;
import com.bebas.org.modules.model.base.model.SysUserModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表 持久层接口
 *
 * @author WuHao
 * @date 2022-05-24 23:43:38
 */
public interface SysUserMapper extends BaseMapper<SysUserModel> {

    IPage<SysUserDTO> selectUnallocatedList(IPage<SysUserDTO> page, @Param("param") SysUserDTO param);

    /**
     * 通过userId获取用户详细信息
     * @param userId
     * @return
     */
    SysUserDTO selectUserDetailById(Long userId);

    SysDeptModel selectUserDeptByUserId(Long userId);

    List<SysPostModel> selectPostsByUserName(String userName);

    IPage<SysUserDTO> selectAllocatedList(IPage<SysUserDTO> page, @Param("param") SysUserDTO param);

    List<SysUserDTO> selectListByParam(SysUserModel param);
}
