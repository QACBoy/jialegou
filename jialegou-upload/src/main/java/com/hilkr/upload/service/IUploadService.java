package com.hilkr.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public interface IUploadService {

    String uploadImage(MultipartFile file);
}
