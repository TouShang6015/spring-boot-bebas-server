package com.bebas.org.modules.webapi.base.tree;

import com.bebas.org.common.utils.tree.TreeBuild;
import com.bebas.org.modules.convert.base.SysDeptConvert;
import com.bebas.org.modules.model.base.dto.SysDeptDTO;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.org.bebasWh.core.model.TreeBaseModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wuhao
 * @date 2022/7/23 18:35
 */
@Service
public class DeptTreeImpl extends TreeBuild<SysDeptDTO> {

    @Override
    protected List<SysDeptDTO> convertTreeModel(List<? extends TreeBaseModel> list) {
        return SysDeptConvert.INSTANCE.convertToDTO((List<SysDeptModel>) list);
    }
}
