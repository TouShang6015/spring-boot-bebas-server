package com.bebas.module.base.web.service.login;

import com.org.bebasWh.utils.result.Result;
import com.bebas.org.modules.model.base.vo.RegisterBodyVo;

import javax.validation.Valid;

/**
 * @author WuHao
 * @date 2022/5/29 16:26
 */
public interface IRegisterService {

    /**
     * 注册接口
     * <p>
     *     userName -- 必填
     *     nickName -- 必填
     *     password -- 密码
     * </p>
     * @param param
     * @return
     */
    Result doRegister(@Valid RegisterBodyVo param);

}
