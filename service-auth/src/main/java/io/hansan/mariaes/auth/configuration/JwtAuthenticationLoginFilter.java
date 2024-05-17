package io.hansan.mariaes.auth.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 18:06
 * @description：TODO
 */
public class JwtAuthenticationLoginFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * 构造函数调用父类构造函数
     */
    public JwtAuthenticationLoginFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws org.springframework.security.core.AuthenticationException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(authRequest);
    }

}
