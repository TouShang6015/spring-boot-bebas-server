package com.bebas.org.common.utils.tree;

import com.org.bebasWh.core.model.TreeBaseModel;

import java.util.List;

/**
 * @author Wuhao
 * @date 2022/7/23 18:17
 */
public interface TreeService<T extends TreeBaseModel> {

    /**
     * 将list转换为树形结构
     *
     * @param list
     * @param <T>
     * @return
     */
    List<T> convertTree(List<T> list);

    /**
     * 根据父节点过滤
     *
     * @param list
     * @param parentId
     * @return
     */
    List<T> filterParent(List<T> list, Long parentId);

}
