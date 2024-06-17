package inha.cumulonimbus_cloud.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://inha-team-04-s3.s3-website-ap-southeast-1.amazonaws.com", "http://inha-team-04-prod-s3.s3-website-ap-southeast-1.amazonaws.com", "http://localhost:3000") // 리액트 앱의 주소 및 S3 주소 추가
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}