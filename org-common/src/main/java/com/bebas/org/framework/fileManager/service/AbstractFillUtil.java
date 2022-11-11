package com.bebas.org.framework.fileManager.service;

import com.bebas.org.common.config.global.UploadConfig;
import com.bebas.org.common.constants.StringPool;
import com.org.bebasWh.exception.CommonException;
import com.org.bebasWh.utils.DateUtils;
import com.org.bebasWh.utils.StringUtils;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Objects;

/**
 * @author wuhao
 * @date 2022/9/23 10:40
 */
public abstract class AbstractFillUtil {

    /**
     * 得到文件扩展名
     *
     * @param fileName 文件名称
     * @return {@link String} 文件后缀
     */
    protected String getFileSuffix(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(StringPool.DOT));
    }

    /**
     * 获取文件md5值
     *
     * @param inputStream 文件输入流
     * @return {@link String} 文件md5值
     */
    protected String getMd5(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(md5.digest()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     */
    public void assertAllowed(MultipartFile file, String[] allowedExtension) {
        int maxSize = UploadConfig.getMaxUploadSize() * 1024 * 1024;
        long size = file.getSize();
        if (size > maxSize) {
            throw new CommonException(StringUtils.format("文件大小不能大约{}M", maxSize));
        }
        int fileNamelength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNamelength > UploadConfig.getMaxFileNameLength()) {
            throw new CommonException("file异常，默认文件名长度不大于" + UploadConfig.getMaxFileNameLength());
        }

        String fileName = file.getOriginalFilename();
        String suffix = getFileSuffix(file.getOriginalFilename());
        if (allowedExtension != null && !isAllowedExtension(suffix, allowedExtension) && allowedExtension != FileTypeConstants.ALL_EXTENSION) {
            if (allowedExtension == FileTypeConstants.IMAGE_EXTENSION) {
                throw new CommonException(StringUtils.format("file异常-文件名：[{}.{}] 图片可使用格式：[bmp, gif, jpg, jpeg, png]", fileName, suffix));
            } else if (allowedExtension == FileTypeConstants.FLASH_EXTENSION) {
                throw new CommonException(StringUtils.format("file异常-文件名：[{}.{}] 图片可使用格式：[swf, flv]", fileName, suffix));
            } else if (allowedExtension == FileTypeConstants.MEDIA_EXTENSION) {
                throw new CommonException(StringUtils.format("file异常-文件名：[{}.{}] 图片可使用格式：[swf, flv, mp3, wav, wma, wmv, mid, avi, mpg,asf, rm, rmvb]", fileName, suffix));
            } else if (allowedExtension == FileTypeConstants.VIDEO_EXTENSION) {
                throw new CommonException(StringUtils.format("file异常-文件名：[{}.{}] 图片可使用格式：[mp4, avi, rmvb]", fileName, suffix));
            } else {
                throw new CommonException(StringUtils.format("file异常-文件名：[{}.{}]", fileName, suffix));
            }
        }

    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param suffix
     * @param allowedExtension
     * @return
     */
    public boolean isAllowedExtension(String suffix, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 编码文件名
     */
    public String extractFilename(String fileName, String suffix) throws IOException {
        return StringUtils.format("{}/{}{}", DateUtils.datePath(), fileName, suffix);
    }

}
