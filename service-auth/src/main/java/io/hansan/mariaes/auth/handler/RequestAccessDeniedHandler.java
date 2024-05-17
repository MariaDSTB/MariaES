package io.hansan.mariaes.auth.handler;

import io.hansan.mariaes.auth.utils.ResponseUtils;
import io.hansan.mariaes.common.data.CommonResult;
import io.hansan.mariaes.common.data.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 20:21
 * @description：权限不够处理器
 */
@Component
public class RequestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException{
        ResponseUtils.writeResponse(response, CommonResult.failed(ResultCode.FORBIDDEN, accessDeniedException.getMessage()));
    }
}
