package com.bebas.org.common.enums.file;

import com.bebas.org.common.enums.BaseEnums;
import lombok.AllArgsConstructor;

/**
 * 文件扩展名枚举
 *
 * @author wuhao
 * @date 2022/9/23 10:49
 */
@AllArgsConstructor
public enum FileExtEnum implements BaseEnums {

    /**
     * jpg文件
     */
    JPG(".jpg", "jpg文件"),
    /**
     * png文件
     */
    PNG(".png", "png文件"),
    /**
     * Jpeg文件
     */
    JPEG(".jpeg", "jpeg文件"),
    /**
     * wav文件
     */
    WAV(".wav", "wav文件"),
    /**
     * md文件
     */
    MD(".md", "markdown文件"),
    /**
     * txt文件
     */
    TXT(".txt", "txt文件");

    private final String key;

    private final String value;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

}
