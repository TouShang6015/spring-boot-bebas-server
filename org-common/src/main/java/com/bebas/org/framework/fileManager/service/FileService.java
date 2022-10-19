package com.bebas.org.framework.fileManager.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件管理业务接口
 * @author wuhao
 * @date 2022/9/23 10:14
 */
public interface FileService extends FileTypeConstants{

    /**
     * 文件上传
     * @param file
     * @param prefix
     * @return
     */
    String upload(MultipartFile file,String prefix,String[] allowedType);

    /**
     * 文件上传
     * @param prefix
     * @param suffix
     * @param inputStream
     * @return
     */
    String upload(String prefix, String suffix,InputStream inputStream);

    /**
     * 文件下载
     * @param path
     * @param outputStream
     */
    String download(String path, OutputStream outputStream) throws IOException;

    default String upload(MultipartFile file,String prefix){
        return upload(file, prefix,IMAGE_EXTENSION);
    }



}
