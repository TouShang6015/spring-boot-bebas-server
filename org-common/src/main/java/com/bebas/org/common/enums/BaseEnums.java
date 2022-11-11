package com.bebas.org.common.enums;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * @author wuhao
 * @date 2022/9/23 10:57
 */
public interface BaseEnums {

    BiFunction<? super BaseEnums[], String, String> getValueByKey = (e, key) ->
            Arrays.stream(e).filter(item -> item.getKey().equals(key)).map(BaseEnums::getValue).findFirst().orElse(null);
    BiFunction<? super BaseEnums[], String, ? extends BaseEnums> getEnumByKey = (e, key) ->
            Arrays.stream(e).filter(item -> item.getKey().equals(key)).findFirst().orElse(null);

    String getKey();

    String getValue();

}
