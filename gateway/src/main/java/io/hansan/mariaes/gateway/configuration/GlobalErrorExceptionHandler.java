package io.hansan.mariaes.gateway.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hansan.mariaes.common.data.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ：何汉叁
 * @date ：2024/5/14 12:59
 * @description：TODO
 */
@Slf4j
@Order(-1)
@Component
public class GlobalErrorExceptionHandler implements ErrorWebExceptionHandler {
    @SuppressWarnings({"rawtypes", "unchecked", "NullableProblems"})
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        //json返回数据
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if(ex instanceof ResponseStatusException){
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }
       try {
           // 将CommonResult对象转换为JSON字符串
           ObjectMapper objectMapper = new ObjectMapper();
           byte[] errorData = objectMapper.writeValueAsBytes(CommonResult.internalServerError(ex.getMessage()));
           // 将JSON字符串写入响应体
           DataBuffer buffer = response.bufferFactory().wrap(errorData);
           return response.writeWith(Mono.just(buffer));
       } catch (JsonProcessingException e) {
            log.error("Error writing response", ex);
            return Mono.error(ex);
        }
    }
}
