package com.hilkr.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 描述: <br>
 * TODO
 *
 * @author sam
 * @create 2019-03-23
 */
@Data
@ConfigurationProperties(prefix = "jialegou.upload")
@Component
public class UploadProperties {

    private String baseUrl;
    private List<String> allowTypes;

}
