package io.hansan.mariaes.gateway.configuration;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import io.hansan.mariaes.common.BizConstants;
/**
 * @author ：何汉叁
 * @date ：2024/5/12 22:00
 * @description：添加头部信息
 */
@Component
public class AuthorizeFilter implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange
                .getRequest()
                .mutate()
                .header(BizConstants.USER_ID_HEADER, StpUtil.getLoginId(BizConstants.USER_ID_UNAUTHORIZED).toString())
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(request).build();
        return chain.filter(newExchange);
    }
}
