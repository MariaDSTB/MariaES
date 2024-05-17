package io.hansan.mariaes.auth.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.hansan.mariaes.common.data.CommonResult;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 20:05
 * @description：TODO
 */
public class ResponseUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void writeResponse(HttpServletResponse response, CommonResult data) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(data));
    }
}
