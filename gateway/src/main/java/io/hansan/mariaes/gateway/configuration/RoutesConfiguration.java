package io.hansan.mariaes.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：何汉叁
 * @date ：2024/5/14 10:56
 * @description：路由配置
 */
@Configuration
public class RoutesConfiguration {
    @Value("${mariaes.front.url}")
    private String frontUrl;
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-user", r->r.path("/user/**")
                        .uri("lb://service-user"))
                .route("service-page", r->r.path("/page/**")
                        .uri("lb://service-page"))
                .route("service-exam", r->r.path("/exam/**")
                        .uri("lb://service-exam"))
                .route("service-question", r->r.path("/question/**")
                        .uri("lb://service-question"))
                .route("service-grade", r->r.path("/grade/**")
                        .uri("lb://service-grade"))
                .route("service-auth", r->r.path("/authorization/**")
                        .uri("lb://service-auth"))
                .route("front", r->r.path("/front/**")
                        .uri(frontUrl))
                .build();
    }
}
