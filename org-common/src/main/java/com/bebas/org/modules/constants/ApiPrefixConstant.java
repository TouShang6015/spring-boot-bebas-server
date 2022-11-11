package com.bebas.org.modules.constants;

/**
 * api请求前缀常量类
 *
 * @author WuHao
 * @date 2022/5/18 17:56
 */
public interface ApiPrefixConstant {

    String MODEL_API = "/module";

    interface Auth {

        String SYSTEM = "/auth/system";

    }

    interface Modules {

        String BASE = MODEL_API + "/base";

        String GENERATE = MODEL_API + "/generate";

        String QUARTZ = MODEL_API + "/quartz";

    }

    interface Common {

        String COMMON = "/common";

    }

}
