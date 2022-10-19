package com.bebas.org.common.utils.tree;

import cn.hutool.core.collection.CollUtil;
import com.org.bebasWh.core.model.TreeBaseModel;
import com.org.bebasWh.utils.OptionalUtil;
import com.org.bebasWh.utils.StringUtils;
import com.bebas.org.common.constants.Constants;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Wuhao
 * @date 2022/7/23 18:28
 */
public abstract class TreeBuild<TreeModel extends TreeBaseModel> implements TreeService<TreeModel> {

    /**
     * 将list转换为树形结构
     * @param list
     * @return
     */
    public List<TreeModel> convertTree(List<TreeModel> list){
        if (CollUtil.isEmpty(list)){
            return CollUtil.newArrayList();
        }
        List<Long> tempList = list.parallelStream().map(TreeModel::getId).collect(Collectors.toList());
        List<TreeModel> returnList = list.stream()
                .filter(item -> !tempList.contains(item.getParentId()))
                .peek(item -> recursionFn(list,item))
                .collect(Collectors.toList());
        if (returnList.isEmpty()) {
            returnList = list;
        }
        return returnList;
    }

    /**
     * 根据父节点过滤
     *
     * @param list
     * @param parentId
     * @return
     */
    @Override
    public List<TreeModel> filterParent(List<TreeModel> list, Long parentId) {
        if (CollectionUtils.isEmpty(list)){
            return list;
        }
        List<TreeModel> filterPIdList = list.stream().filter(item -> item.getId().equals(parentId)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(filterPIdList)){
            List<TreeModel> treeChildrenList = list.stream()
                    .flatMap(item -> this.convertTreeModel(OptionalUtil.ofNullList(item.getChildren())).stream())
                    .collect(Collectors.toList());
            return this.filterParent(treeChildrenList,parentId);
        }else{
            return filterPIdList;
        }
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    protected void recursionFn(List<TreeModel> list, TreeModel t){
        // 得到子节点列表
        List<TreeModel> childList = getChildList(list, t);
        t.setChildren(childList);
        for (TreeModel tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     * @param list
     * @param t
     * @return
     */
    protected List<TreeModel> getChildList(List<TreeModel> list, TreeModel t){
        return list.stream().filter(item -> Objects.equals(item.getParentId(), t.getId())).collect(Collectors.toList());
    }

    /**
     * 判断是否有子节点
     * @param list
     * @param t
     * @return
     */
    protected boolean hasChild(List<TreeModel> list, TreeModel t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 内链域名特殊字符替换
     * @param path
     * @return
     */
    protected String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{Constants.HTTP, Constants.HTTPS},
                new String[]{"", ""});
    }

    protected abstract List<TreeModel> convertTreeModel(List<? extends TreeBaseModel> list);

}
