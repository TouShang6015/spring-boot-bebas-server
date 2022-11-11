package com.bebas.org.common.config.exception;

import com.org.bebasWh.utils.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wuhao
 * @date 2022/10/5 22:45
 */
@RestController
public class ErrorController {

    @RequestMapping("/error/print")
    public Result printError(HttpServletRequest request) {
        String errorMessage = (String) request.getAttribute("error.message");
        return Result.fail(errorMessage);
    }

}
