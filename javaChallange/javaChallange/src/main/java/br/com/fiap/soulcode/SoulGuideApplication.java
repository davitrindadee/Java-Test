package br.com.fiap.soulcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SoulGuideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoulGuideApplication.class, args);
    }
}