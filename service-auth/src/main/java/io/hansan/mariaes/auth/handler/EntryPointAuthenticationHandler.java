package io.hansan.mariaes.auth.handler;

import io.hansan.mariaes.auth.utils.ResponseUtils;
import io.hansan.mariaes.common.data.CommonResult;
import io.hansan.mariaes.common.data.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 20:28
 * @description：TODO
 */
@Component
public class EntryPointAuthenticationHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ResponseUtils.writeResponse(response, CommonResult.failed(ResultCode.FORBIDDEN, authException.getMessage()));
    }
}
