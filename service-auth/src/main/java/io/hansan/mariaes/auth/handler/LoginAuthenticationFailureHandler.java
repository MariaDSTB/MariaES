package io.hansan.mariaes.auth.handler;

import io.hansan.mariaes.auth.utils.ResponseUtils;
import io.hansan.mariaes.common.data.CommonResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 20:01
 * @description：TODO
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        CommonResult result;
        if (exception instanceof BadCredentialsException) {
            result = CommonResult.success(null, "用户名或密码错误");
        } else {
            result = CommonResult.success(null,"登录失败");
        }
        ResponseUtils.writeResponse(response, result);
    }
}
