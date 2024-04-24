package io.hansan.servicequestion.handler;

/**
 * @author ：何汉叁
 * @date ：2024/4/23 22:06
 * @description：TODO
 */
import io.hansan.common.data.ErrorMessage;
import io.hansan.common.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.logging.ErrorManager;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessage notLoginException() {
        return new ErrorMessage("Not Login .");
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException() {
        return new ErrorMessage("The resource you requested isn't exist .");
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException() {
        return new ErrorMessage("The request is invalid , place check your argument.");
    }
}