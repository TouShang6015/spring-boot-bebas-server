package com.bebas.org.framework.fileManager.service;

import cn.hutool.core.lang.Singleton;
import com.bebas.org.common.constants.ResourceConfigConstant;
import com.bebas.org.common.constants.StringPool;
import com.bebas.org.modules.model.base.vo.baseResource.ResourceMainVO;
import com.bebas.org.modules.webapi.base.ResourceConfigWebApi;
import com.org.bebasWh.exception.CommonException;
import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.uuid.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author wuhao
 * @date 2022/9/23 10:28
 */
@Slf4j
public abstract class AbstractFileHandle extends AbstractFillUtil implements FileService {

    protected ResourceMainVO mainVO;

    public AbstractFileHandle() {
        this.mainVO = Singleton.get(ResourceMainVO.class);
    }

    /**
     * 文件上传
     *
     * @param file   文件
     * @param prefix 后缀
     * @return
     */
    @Override
    public String upload(MultipartFile file, String prefix, String[] allowedType) {
        try {
            // 文件校验
            super.assertAllowed(file, allowedType);
            // 将文件流转为字符串
            String md5 = IdUtils.fastUUID();
            // 文件后缀
            String fileSuffix = super.getFileSuffix(file.getOriginalFilename());
            // 文件名称
            String fileName = super.extractFilename(md5, fileSuffix);
            // 获取文件路径
            String filePath = this.getFilePath(prefix, fileName);
            if (!exists(filePath)) {
                handleUpload(prefix, fileName, file.getInputStream());
            }
            return this.getFileAccessUrl(prefix, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException("文件上传失败！");
        }
    }

    @Override
    public String upload(String prefix, String suffix, InputStream inputStream) {
        try {
            if (StringUtils.isEmpty(suffix)) {
                throw new IOException("文件后缀为空！");
            }
            // 将文件流转为字符串
            String md5 = IdUtils.fastSimpleUUID();
            // 文件名称
            String fileName = super.extractFilename(md5, suffix);
            // 获取文件路径
            String filePath = this.getFilePath(prefix, fileName);
            if (!exists(filePath)) {
                handleUpload(prefix, fileName, inputStream);
            }
            return this.getFileAccessUrl(prefix, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException("文件上传失败！");
        }
    }

    /**
     * 文件下载
     *
     * @param path
     * @param outputStream
     */
    @Override
    public String download(String path, OutputStream outputStream) throws IOException {
        String filePath = this.getPath() + path;
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                outputStream.write(b, 0, length);
            }
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException("文件下载失败！");
        } finally {
            IOUtils.close(outputStream);
            IOUtils.close(fis);
        }
    }

    /**
     * 获取资源存储路径
     *
     * @return
     */
    protected String getPath() {
        return mainVO.getFileSavePathLinux();
    }

    /**
     * 获取文件的本地路径
     *
     * @param prefix
     * @param fileName
     * @return
     */
    protected String getFilePath(String prefix, String fileName) {
        return this.getPath() + prefix + StringPool.SLASH + fileName;
    }

    /**
     * 文件上传操作
     *
     * @param prefix
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     */
    protected abstract void handleUpload(String prefix, String fileName, InputStream inputStream) throws IOException;

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@link Boolean}
     */
    protected abstract Boolean exists(String filePath);

    /**
     * 获取文件访问url
     *
     * @param filePath 文件路径
     * @return {@link String}
     */
    public abstract String getFileAccessUrl(String prefix, String filePath);

}
