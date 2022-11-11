package com.bebas.org.modules.model.base.vo.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.net.URL;
import java.util.Date;

/**
 * @author wyj
 * @date 2022/10/13 14:59
 */
@Data
@EqualsAndHashCode
public class SysUserExcelVo {

    /**
     * 用户昵称
     */
    @ExcelProperty(value = "用户昵称", order = 1)
    private String nickName;
    /**
     * 用户账号
     */
    @ExcelProperty(value = "用户账号", order = 2)
    private String userName;
    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门名称", order = 3)
    private String deptName;
    /**
     * 用户邮箱
     */
    @ExcelProperty(value = "用户邮箱")
    private String email;
    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    @ExcelProperty(value = "用户性别")
    private String sex;
    @ExcelProperty(value = "头像")
    private URL avatarURL;
    /**
     * 帐号状态（0正常 1停用）
     */
    @ExcelProperty(value = "帐号状态")
    private String status;
    /**
     * 最后登录IP
     */
    @ExcelProperty(value = "最后登录IP")
    private String loginIp;
    /**
     * 最后登录时间
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "最后登录时间")
    private Date loginDate;

    @ExcelProperty(value = "创建时间")
    private String createTime;
    @ExcelProperty(value = "修改时间")
    private String updateTime;

    /**
     * 头像地址
     */
    @ExcelIgnore
    private String avatar;

}
