package com.hilkr.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hilkr.common.enums.ExceptionEnum;
import com.hilkr.common.exception.JialegouException;
import com.hilkr.upload.config.UploadProperties;
import com.hilkr.upload.service.IUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-03-21
 */
@Service
@Slf4j
// @EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadProperties prop;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public String uploadImage(MultipartFile file) {
        //对文件进行校验
        //对文件格式进行校验
        String contentType = file.getContentType();
        if (!prop.getAllowTypes().contains(contentType)) {
            throw new JialegouException(ExceptionEnum.INVALID_FILE_FORMAT);
        }
        //检验文件内容
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                log.info("【 文件上传 】上传文件格式错误");
                throw new JialegouException(ExceptionEnum.INVALID_FILE_FORMAT);
            }
        } catch (IOException e) {
            log.info("【 文件上传 】文件上传失败", e);
            throw new JialegouException(ExceptionEnum.INVALID_FILE_FORMAT);
        }
        //保存图片
        try {
            String extensionName = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extensionName, null);
            //返回保存图片的完整url
            return prop.getBaseUrl() + storePath.getFullPath();
        } catch (IOException e) {
            throw new JialegouException(ExceptionEnum.UPLOAD_IMAGE_EXCEPTION);
        }
    }
}
