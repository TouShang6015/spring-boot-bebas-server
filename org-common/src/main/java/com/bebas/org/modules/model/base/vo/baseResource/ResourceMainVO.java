package com.bebas.org.modules.model.base.vo.baseResource;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wuhao
 * @date 2022/9/23 9:21
 */
@Data
public class ResourceMainVO implements Serializable {

    /**
     * 文件保存目录 linux （注意最后要带/）
     */
    private String fileSavePathLinux;
    /**
     * 文件保存目录 window  （注意最后要带/）
     */
    private String fileSavePathWin;
    /**
     * 是否开启注册
     */
    private Boolean registerOpen;
    /**
     * 是否开启登陆验证码
     */
    private Boolean authCodeOpen;
    /**
     * 最大登陆人数
     */
    private Integer maxUserLogin;
    /**
     * 静态资源访问域名 （不需要带/）
     */
    private String staticWebsite;
    /**
     * 网站域名
     */
    private String website;

}
