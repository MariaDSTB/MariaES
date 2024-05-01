package io.hansan.mariaes.user.handler;

import io.hansan.mariaes.common.data.ErrorMessage;
import io.hansan.mariaes.common.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * @author ：何汉叁
 * @date ：2024/4/29 8:13
 * @description：TODO
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage notLoginException() {
        return new ErrorMessage("Not Login");
    }
    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFountException() {
        return new ErrorMessage("The resource you requested id not found");
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException() {
        return new ErrorMessage("The request in invalid, place check your argument");
    }
}
