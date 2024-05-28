package io.hansan.mariaes.auth.handler;

import io.hansan.mariaes.common.data.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

/**
 * @author ：何汉叁
 * @date ：2024/5/28 17:01
 * @description：TODO
 */
@RestControllerAdvice
public class ConnectExceptionHandler {

    @ExceptionHandler(value = ConnectException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage RedisConnectException() {
        return new ErrorMessage("Redis Connect Error");
    }
}
