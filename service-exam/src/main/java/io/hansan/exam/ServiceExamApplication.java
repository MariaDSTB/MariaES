package io.hansan.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServiceExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceExamApplication.class, args);
    }

}
