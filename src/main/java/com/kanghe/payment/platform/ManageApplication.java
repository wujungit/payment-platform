package com.kanghe.payment.platform;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @Author: W_jun1
 * @Date: 2019/5/27 15:16
 * @Description: ManageApplication
 */
@SpringBootApplication
public class ManageApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ManageApplication.class).run(args);
    }
}
