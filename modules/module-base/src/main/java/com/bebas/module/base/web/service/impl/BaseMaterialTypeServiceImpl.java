package com.bebas.module.base.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.bebas.module.base.mapper.BaseMaterialTypeMapper;
import com.bebas.module.base.web.service.IBaseMaterialTypeService;
import com.bebas.org.common.constants.Constants;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.utils.MessageUtils;
import com.bebas.org.modules.model.base.model.BaseMaterialTypeModel;
import com.bebas.org.modules.model.base.model.SysDeptModel;
import com.org.bebasWh.exception.BusinessException;
import com.org.bebasWh.mapper.cache.ServiceImpl;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 素材分类 业务实现类
 *
 * @author WuHao
 * @date 2022-09-09 10:14:23
 */
@Service
public class BaseMaterialTypeServiceImpl extends ServiceImpl<BaseMaterialTypeMapper, BaseMaterialTypeModel> implements IBaseMaterialTypeService {

    @Override
    public boolean save(BaseMaterialTypeModel entity) {
        BaseMaterialTypeModel parentModel = super.getById(entity.getParentId());
        if (Objects.nonNull(parentModel)) {
            // 如果父节点不为正常状态,则不允许新增子节点
            if (Constants.Status.NO_NORMAL.equals(parentModel.getStatus())) {
                throw new BusinessException(MessageUtils.message("common.status.no_normal"));
            }
            entity.setAncestors(parentModel.getAncestors() + StringPool.COMMA + entity.getParentId());
        } else {
            entity.setAncestors(SysDeptModel.DEFAULT_ANCESTORS);
        }
        return super.save(entity);
    }

    @Override
    public boolean updateById(BaseMaterialTypeModel entity) {
        BaseMaterialTypeModel newParentModel = super.getById(entity.getParentId());
        BaseMaterialTypeModel oldModel = super.getById(entity.getId());
        List<BaseMaterialTypeModel> updateList = CollUtil.newArrayList();
        if (Objects.nonNull(newParentModel) && Objects.nonNull(oldModel)) {
            String newAncestors = newParentModel.getAncestors() + StringPool.COMMA + newParentModel.getId();
            String oldAncestors = oldModel.getAncestors();
            entity.setAncestors(newAncestors);
            updateList.addAll(updateChildren(entity.getId(), newAncestors, oldAncestors));
        }
        updateList.add(entity);
        boolean result = super.updateBatchById(updateList);
        if (Constants.Status.NORMAL.equals(entity.getStatus()) && StringUtils.isNotEmpty(entity.getAncestors()) && !StringUtils.equals(SysDeptModel.DEFAULT_ANCESTORS, entity.getAncestors())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentStatusNormal(entity);
        }
        return result;
    }

    /**
     * 修改子元素关系
     *
     * @param id           被修改的ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public List<BaseMaterialTypeModel> updateChildren(Long id, String newAncestors, String oldAncestors) {
        List<BaseMaterialTypeModel> list = OptionalUtil.ofNullList(baseMapper.selectChildrenById(id));
        list.parallelStream().forEach(item -> {
            item.setAncestors(item.getAncestors().replaceFirst(oldAncestors, newAncestors));
        });
        return list;
    }

    /**
     * 修改父级状态
     *
     * @param model
     */
    private void updateParentStatusNormal(BaseMaterialTypeModel model) {
        String ancestors = model.getAncestors();
        List<Long> deptIds = Arrays.stream(ancestors.split(StringPool.COMMA)).map(Long::valueOf).collect(Collectors.toList());
        lambdaUpdate().set(BaseMaterialTypeModel::getStatus, Constants.Status.NORMAL).in(BaseMaterialTypeModel::getId, deptIds).update();
    }

}
