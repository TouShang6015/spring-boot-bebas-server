package com.bebas.module.base.core.dataPermission.context;

import com.bebas.module.base.core.dataPermission.range.PerRangeStrategy;
import com.org.bebasWh.exception.CommonException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuhao
 * @date 2022/10/1 16:46
 */
@Component
public class PerRangeContext {

    @Resource
    private List<PerRangeStrategy> preRangeStrategyList;

    public PerRangeStrategy getPerRangeService(String value) {
        return preRangeStrategyList.stream()
                .filter(p -> p.getRangeSign().equals(value))
                .findFirst()
                .orElseThrow(() -> new CommonException("无法获取权限服务上下文"));
    }

}
