package com.hackathon.ngts.helping.config;

import com.hackathon.ngts.helping.auth.WxAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wuzhenjie on 2019-08-29.
 */
@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final WeixinConfig weixinConfig;

    @Value("${security.urlPatterns}")
    private String[] urlPatterns;


    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WxAuthFilter(weixinConfig));
        registration.addUrlPatterns(urlPatterns);
        registration.setName("WxAuthFilter");
        registration.setOrder(1);
        return registration;
    }
}
