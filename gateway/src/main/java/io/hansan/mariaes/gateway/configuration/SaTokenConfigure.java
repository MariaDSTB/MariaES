package io.hansan.mariaes.gateway.configuration;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author ：何汉叁
 * @date ：2024/5/18 22:46
 * @description：注册【So-token权限认证】全局过滤器
 */
@Slf4j
@Configuration
public class SaTokenConfigure {
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/api/authorization/**")
                .setAuth(obj -> {
                    SaRouter.match("/**","/api/authorization/**", r -> StpUtil.checkLogin());
                    SaRouter.match("/questions/**", r -> StpUtil.checkRole("admin"));
                    SaRouter.match("/pages/**", r -> StpUtil.checkPermission("page.*"));
                })
                .setError(e -> {
                    if (e instanceof NotLoginException) {
                        ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return "401 unAuthorized";
                    }
                    log.error("Unhandled error during SaReactorFilter", e);
                    return e.toString();
                });
    }
}
