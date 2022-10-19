package com.bebas.module.base.web.controller;

import com.org.bebasWh.utils.StringUtils;
import com.org.bebasWh.utils.result.Result;
import com.bebas.org.common.constants.ResourceConfigConstant;
import com.bebas.org.common.enums.file.FilePrefixEnum;
import com.bebas.org.common.utils.file.FileUtil;
import com.bebas.org.framework.fileManager.service.FileService;
import com.bebas.org.framework.fileManager.service.FileTypeConstants;
import com.bebas.org.modules.model.base.vo.baseResource.ResourceMainVO;
import com.bebas.org.modules.webapi.base.ResourceConfigWebApi;
import com.bebas.org.modules.constants.ApiPrefixConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用请求处理
 *
 * @author WuHao
 */
@RestController
@RequestMapping(ApiPrefixConstant.Common.COMMON + "/file")
@Api(tags = "公共common 控制器")
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Resource
    private ResourceConfigWebApi resourceConfigWebApiService;
    @Resource
    private FileService fileService;

    /**
     * 通用下载请求
     *
     * @param path 文件相对路径
     * @param downloadName 文件下载名称
     * @param delete   是否删除
     */
    @ApiOperation(value="通用下载请求",notes = "通用下载请求",httpMethod="GET")
    @GetMapping("/download")
    public void fileDownload(String path,String downloadName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtil.checkAllowDownload(path)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", path));
            }
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtil.setAttachmentResponseHeader(response, downloadName);
            String filePath = fileService.download(path, response.getOutputStream());
            if (delete) {
                FileUtil.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @ApiOperation(value="通用上传请求（单个）",notes = "通用上传请求（单个）",httpMethod="POST",response= Result.class)
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file) throws Exception {
        try {
            ResourceMainVO resource = resourceConfigWebApiService.queryValueByConfigKey(ResourceConfigConstant.MAIN_KEY, ResourceMainVO.class);
            // 上传文件
            String filePath = fileService.upload(file, FilePrefixEnum.UPLOAD.getKey(), FileTypeConstants.ALL_EXTENSION);

            String url = resource.getStaticWebsite() + filePath;
            return Result.success()
                    .put("url", url)
                    .put("path", filePath)
                    .put("originalFilename", file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("文件上传失败");
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @ApiOperation(value="通用上传请求（多个）",notes = "通用上传请求（多个）",httpMethod="POST",response= Result.class)
    @PostMapping("/uploads")
    public Result uploadFiles(List<MultipartFile> files) throws Exception {
        try {
            ResourceMainVO resource = resourceConfigWebApiService.queryValueByConfigKey(ResourceConfigConstant.MAIN_KEY, ResourceMainVO.class);
            // 上传文件路径
            List<Map<String,Object>> fileList = new ArrayList<>();
            for (MultipartFile file : files) {
                String filePath = fileService.upload(file, FilePrefixEnum.UPLOAD.getKey(), FileTypeConstants.ALL_EXTENSION);
                String url = resource.getStaticWebsite() + filePath;
                Map<String, Object> map = new HashMap<>();
                map.put("url", url);
                map.put("path", filePath);
                map.put("originalFilename", file.getOriginalFilename());
                fileList.add(map);
            }
            return Result.success(fileList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("批量文件上传失败");
        }
    }

}
