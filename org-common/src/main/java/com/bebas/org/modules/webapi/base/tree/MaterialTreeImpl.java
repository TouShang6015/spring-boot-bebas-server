package com.bebas.org.modules.webapi.base.tree;

import com.org.bebasWh.core.model.TreeBaseModel;
import com.bebas.org.common.utils.tree.TreeBuild;
import com.bebas.org.modules.convert.base.BaseMaterialTypeConvert;
import com.bebas.org.modules.model.base.dto.BaseMaterialTypeDTO;
import com.bebas.org.modules.model.base.model.BaseMaterialTypeModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wuhao
 * @date 2022/7/23 18:35
 */
@Service
public class MaterialTreeImpl extends TreeBuild<BaseMaterialTypeDTO> {

    @Override
    protected List<BaseMaterialTypeDTO> convertTreeModel(List<? extends TreeBaseModel> list) {
        return BaseMaterialTypeConvert.INSTANCE.convertToDTO((List<BaseMaterialTypeModel>) list);
    }
}
