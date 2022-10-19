package com.bebas.module.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门表 持久层接口
 *
 * @author WuHao
 * @date 2022-05-25 19:02:33
 */
public interface SysDeptMapper extends BaseMapper<SysDeptModel> {

    @Select("select count(*) from sys_dept where status = 0 and del_flag = '0' and find_in_set(#{deptId}, ancestors)")
    int selectNormalChildrenDeptById(@Param("deptId") Long deptId);

    @Select("select * from sys_dept where find_in_set(#{deptId}, ancestors)")
    List<SysDeptModel> selectChildrenDeptById(@Param("deptId") Long deptId);
}
