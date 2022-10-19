package com.bebas.org.modules.webapi.base;

import com.bebas.org.modules.model.base.model.SysMenuModel;

import java.util.List;

/**
 * @author wuhao
 * @date 2022/9/6 15:28
 */
public interface ISysMenuWebApi {

    List<SysMenuModel> listByParamWebApi(SysMenuModel model);

}
