package tn.enis.fooddelivery.delivery;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@EnableFeignClients(basePackages = "tn.enis.fooddelivery.delivery.client")
public class DeliveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner testDb(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection()) {
                System.out.println("Connected to DB: " + conn.getMetaData().getURL());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
