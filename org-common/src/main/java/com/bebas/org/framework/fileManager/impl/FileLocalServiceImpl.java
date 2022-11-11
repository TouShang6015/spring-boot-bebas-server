package com.bebas.org.framework.fileManager.impl;

import com.bebas.org.common.constants.StringPool;
import com.bebas.org.common.enums.file.FileExtEnum;
import com.bebas.org.framework.fileManager.service.AbstractFileHandle;
import com.bebas.org.modules.webapi.base.ResourceConfigWebApi;
import com.org.bebasWh.exception.CommonException;
import com.org.bebasWh.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Objects;

import static com.bebas.org.common.enums.file.FileExtEnum.values;

/**
 * @author wuhao
 * @date 2022/9/23 10:21
 */
@Service
@ConditionalOnExpression("'${plugins.fileUpload}'.equals('local')")
public class FileLocalServiceImpl extends AbstractFileHandle {

    @Autowired
    private ApplicationContext applicationContext;

    public FileLocalServiceImpl(ResourceConfigWebApi resourceConfigWebApi) {
        super(resourceConfigWebApi);
    }

    /**
     * 文件上传操作
     *
     * @param prefix
     * @param fileName
     * @param inputStream
     * @return
     */
    @Override
    public void handleUpload(String prefix, String fileName, InputStream inputStream) throws IOException {
        String localPath = this.getPath();
        String filePath = this.getFilePath(prefix, fileName);
        // 判断目录是否存在
        File directory = new File(localPath + prefix + StringPool.SLASH + DateUtils.datePath());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new CommonException("创建目录失败");
            }
        }
        // 写入文件
        File file = new File(filePath);
        String ext = StringPool.DOT + fileName.split("\\.")[1];
        FileExtEnum fileExtEnum = (FileExtEnum) FileExtEnum.getEnumByKey.apply(values(), ext);
        switch (Objects.requireNonNull(fileExtEnum)) {
            case MD:
            case TXT:
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                while (reader.ready()) {
                    writer.write((char) reader.read());
                }
                writer.flush();
                writer.close();
                reader.close();
                break;
            default:
                BufferedInputStream bis = new BufferedInputStream(inputStream);
                BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
                byte[] bytes = new byte[1024];
                int length;
                while ((length = bis.read(bytes)) != -1) {
                    bos.write(bytes, 0, length);
                }
                bos.flush();
                bos.close();
                bis.close();
                break;
        }
        inputStream.close();
    }

    @Override
    protected String getPath() {
        String osName = applicationContext.getEnvironment().getProperty("os.name").toLowerCase(Locale.ROOT);
        if (osName.contains("window")) {
            return this.mainVO.getFileSavePathWin();
        } else if (osName.contains("linux")) {
            return this.mainVO.getFileSavePathLinux();
        }
        return super.getPath();
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return {@link Boolean}
     */
    @Override
    public Boolean exists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 获取文件访问url
     *
     * @param prefix   前缀
     * @param filePath 文件路径
     * @return {@link String}
     */
    @Override
    public String getFileAccessUrl(String prefix, String filePath) {
        return StringPool.SLASH + prefix + StringPool.SLASH + filePath;
    }
}
