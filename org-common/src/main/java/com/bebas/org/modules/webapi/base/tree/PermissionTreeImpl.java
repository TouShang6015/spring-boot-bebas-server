package com.bebas.org.modules.webapi.base.tree;

import com.bebas.org.common.utils.tree.TreeBuild;
import com.bebas.org.modules.convert.base.SysPermissionConvert;
import com.bebas.org.modules.model.base.dto.SysPermissionDTO;
import com.bebas.org.modules.model.base.model.SysPermissionModel;
import com.org.bebasWh.core.model.TreeBaseModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuhao
 * @date 2022/10/4 13:35
 */
@Service
public class PermissionTreeImpl extends TreeBuild<SysPermissionDTO> {

    @Override
    protected List<SysPermissionDTO> convertTreeModel(List<? extends TreeBaseModel> list) {
        return SysPermissionConvert.INSTANCE.convertToDTO((List<SysPermissionModel>) list);
    }

}
