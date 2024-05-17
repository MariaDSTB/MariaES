package io.hansan.mariaes.auth.handler;

import io.hansan.mariaes.auth.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 19:43
 * @description：TODO
 */
@Component
@Slf4j
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成token
        String accessToken = jwtTokenUtils.generateToken(userDetails.getUsername());
        //生成refreshToken
//        String  refreshToken = jwtTokenUtils.generateRefreshToken(accessToken);

    }
    public void renderToken(HttpServletResponse response, String token) {

    }
}
