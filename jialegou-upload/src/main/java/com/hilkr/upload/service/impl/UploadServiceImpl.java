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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
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
        /**
         * 1.图片信息校验
         *      1)校验文件类型
         *      2)校验图片内容
         * 2.保存图片
         *      1)生成保存目录
         *      2)保存图片
         *      3)拼接图片地址
         */
        try {
            String type = file.getContentType();
            if (!prop.getAllowTypes().contains(type)) {
                log.info("上传文件失败，文件类型不匹配：{}", type);
                throw new JialegouException(ExceptionEnum.INVALID_FILE_FORMAT);
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                log.info("上传失败，文件内容不符合要求");
                throw new JialegouException(ExceptionEnum.INVALID_FILE_FORMAT);
            }

            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), getExtension(file.getOriginalFilename()), null);
            //返回保存图片的完整url
            String url = prop.getBaseUrl() + storePath.getFullPath();
            log.info("保存图片的完整url:{}",url);
            return url;
        } catch (IOException e) {
            throw new JialegouException(ExceptionEnum.UPLOAD_IMAGE_EXCEPTION);
        }
    }

    public String getExtension(String fileName) {
        return StringUtils.substringAfterLast(fileName, ".");
    }
}

