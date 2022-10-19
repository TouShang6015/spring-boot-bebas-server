package com.bebas.org.modules.webapi.base.tree;

import com.org.bebasWh.core.model.TreeBaseModel;
import com.bebas.org.common.utils.tree.TreeBuild;
import com.bebas.org.modules.convert.base.SysMenuConvert;
import com.bebas.org.modules.model.base.dto.SysMenuDTO;
import com.bebas.org.modules.model.base.model.SysMenuModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wuhao
 * @date 2022/7/23 18:35
 */
@Service
public class MenuTreeImpl extends TreeBuild<SysMenuDTO> {

    @Override
    protected List<SysMenuDTO> convertTreeModel(List<? extends TreeBaseModel> list) {
        return SysMenuConvert.INSTANCE.convertToDTO((List<SysMenuModel>) list);
    }

}
