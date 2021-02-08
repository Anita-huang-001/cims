package com.mi.cims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * ClassName: ClientApplication
 * Function:  小程序服务接口
 *
 * @author	       孙忠飞
 * @date 	  2021年1月26日 上午9:07:48
 * @version	  V1.0.0
 */
@SpringBootApplication
@MapperScan("com.mi.cims.dao")
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
