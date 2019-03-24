package com.hilkr.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 描述:
 * TODO
 *
 * @author sam
 * @create 2019-03-21
 */
public interface IUploadService {

    String uploadImage(MultipartFile file);
}
