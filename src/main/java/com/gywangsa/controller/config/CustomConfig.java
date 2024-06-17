package com.gywangsa.controller.config;

import com.gywangsa.controller.formatter.LocalDateTimeFormatter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Log4j2
//LocalDateFormatter 클래스 사용 가능하게 등록해주는 Config
public class CustomConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {

        log.info("=====================================");
        log.info("addFormatters 실행");

        registry.addFormatter(new LocalDateTimeFormatter());
    }

    //CORS 설정
//   @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        log.info("=====================================");
//        log.info("addCorsMappings 실행");
//        registry.addMapping("/**") //경로 설정 : 전부
//                .maxAge(500)
//                .allowedMethods("GET","POST", "PUT", "DELETE", "HEAD", "OPTIONS") //호출을 설정
//                .allowedOrigins("http://localhost:3000"); //어디에서 들어오는 경로 설정 : 3000
//
//    }
}
