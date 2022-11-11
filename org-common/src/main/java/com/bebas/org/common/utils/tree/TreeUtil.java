package com.bebas.org.common.utils.tree;

import cn.hutool.core.collection.CollUtil;
import com.org.bebasWh.utils.bean.ReflectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树构建 工具类
 * <p>实体中需要包含 id、parentId、chidren字段</p>
 *     <ul>
 *         <li>ID 主键</li>
 *         <li>PARENT_ID 父节点id</li>
 *         <li>CHILDREN 子节点</li>
 *     </ul>
 * <p>* 构建树形结构Util</p>
 * <p></p>
 *
 * @author WuHao
 * @date 2022/5/30 11:32
 * @deprecated 通过反射实现，效率非常低！不推荐使用
 */
public class TreeUtil {

    public static final Long PARENT_ID_DEFAULT = 0L;

    private final static String ID = "id";

    private final static String PARENT_ID = "parentId";

    private final static String CHILDREN = "children";

    /**
     * 将list转换为树形结构
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> convertTree(List<T> list) {
        List<T> returnList = CollUtil.newArrayList();
        List<Long> tempList = CollUtil.newArrayList();
        if (CollUtil.isEmpty(list)) {
            return CollUtil.newArrayList();
        }
        list.forEach(item -> tempList.add(ReflectUtils.invokeGetter(item, ID)));
        for (T temp : list) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(ReflectUtils.invokeGetter(temp, PARENT_ID))) {
                recursionFn(list, temp);
                returnList.add(temp);
            }
        }
        if (returnList.isEmpty()) {
            returnList = list;
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private static <T> void recursionFn(List<T> list, T t) {
        // 得到子节点列表
        List<T> childList = getChildList(list, t);
        ReflectUtils.invokeSetter(t, CHILDREN, childList);
        for (T tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }


    /**
     * 得到子节点列表
     *
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    private static <T> List<T> getChildList(List<T> list, T t) {
        List<T> tlist = new ArrayList<T>();
        for (T n : list) {
            if (Objects.equals(ReflectUtils.invokeGetter(n, PARENT_ID), ReflectUtils.invokeGetter(t, ID))) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     *
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    private static <T> boolean hasChild(List<T> list, T t) {
        return getChildList(list, t).size() > 0;
    }


}
