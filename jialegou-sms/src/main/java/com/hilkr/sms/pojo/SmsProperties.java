package com.hilkr.sms.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
// @ConfigurationProperties(prefix = "jialegou.sms")
@Configuration
public class SmsProperties {

    @Value("${jialegou.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${jialegou.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${jialegou.sms.signName}")
    private String signName;

    @Value("${jialegou.sms.verifyCodeTemplate}")
    private String verifyCodeTemplate;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getVerifyCodeTemplate() {
        return verifyCodeTemplate;
    }

    public void setVerifyCodeTemplate(String verifyCodeTemplate) {
        this.verifyCodeTemplate = verifyCodeTemplate;
    }
}
