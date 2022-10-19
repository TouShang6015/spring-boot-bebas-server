package com.bebas.org.common.enums.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wuhao
 * @date 2022/9/23 15:55
 */
@Getter
@AllArgsConstructor
public enum FilePrefixEnum {

    UPLOAD("upload"),

    DOWNLOAD("download")

    ;

    private final String key;

}
