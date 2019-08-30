package com.hackathon.ngts.helping.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@ConfigurationProperties(prefix = "weixin")
@Configuration
@Data
public class WeixinConfig {

    private String appId;

    private String appSecret;

}
