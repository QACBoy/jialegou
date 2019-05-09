package com.hilkr.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
@Data
@ConfigurationProperties(prefix = "jialegou.upload")
@Component
public class UploadProperties {

    private String baseUrl;
    private List<String> allowTypes;

}
