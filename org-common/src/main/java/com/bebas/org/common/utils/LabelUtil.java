package com.bebas.org.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.func.LambdaUtil;
import cn.hutool.core.util.ReflectUtil;
import com.bebas.org.modules.model.base.vo.LabelOption;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wuHao
 * @date 2022/11/11 10:57
 */
@Slf4j
public class LabelUtil {

    private List<?> list;

    public static LabelUtil setValue(List<?> list) {
        LabelUtil _build = new LabelUtil();
        _build.list = list;
        return _build;
    }

    /**
     * 构建下拉
     *
     * @param prop1 label
     * @param prop2 value
     * @param <P1>
     * @param <P2>
     * @return
     */
    public <P1, P2> List<LabelOption<String, String>> buildSelect(Func1<P1, ?> prop1, Func1<P2, ?> prop2) {
        Assert.notNull(prop1);
        Assert.notNull(prop2);
        if (CollUtil.isEmpty(this.list)) {
            log.error("构建下拉失败，列表为空");
            return CollUtil.newArrayList();
        }
        if (Objects.isNull(prop1) || Objects.isNull(prop2)) {
            log.error("构建下拉失败，参数为空");
            return CollUtil.newArrayList();
        }
        String prop1FieldName = LambdaUtil.getFieldName(prop1);
        String prop2FieldName = LambdaUtil.getFieldName(prop2);
        return this.list.stream().map(item -> {
            LabelOption<String, String> optionLabel = new LabelOption<>();
            Object label = ReflectUtil.getFieldValue(item, prop1FieldName);
            Object value = ReflectUtil.getFieldValue(item, prop2FieldName);
            optionLabel.setLabel(String.valueOf(label));
            optionLabel.setValue(String.valueOf(value));
            return optionLabel;
        }).collect(Collectors.toList());
    }

}
