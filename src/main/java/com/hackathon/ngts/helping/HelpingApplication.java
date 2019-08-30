package com.hackathon.ngts.helping;

import com.hackathon.ngts.helping.config.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class HelpingApplication {

    @Value("${application.url}")
    private String appBaseUrl;

    public static void main(String[] args) {
        SpringApplication.run(HelpingApplication.class, args);

    }

    @PostConstruct
    public void afterInit() {
        Constant.baseUrl = appBaseUrl;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.additionalMessageConverters(new WxMappingJackson2HttpMessageConverter()).build();
    }

    @GetMapping("MP_verify_7dFY0oDSFDqA98IL.txt")
    public String home(String echostr) {
        return "7dFY0oDSFDqA98IL";
    }

    private class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        private WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            setSupportedMediaTypes(mediaTypes);
        }
    }

}
