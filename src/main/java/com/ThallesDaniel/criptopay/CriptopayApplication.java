package com.ThallesDaniel.criptopay;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
public class CriptopayApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CriptopayApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);

    }
}
