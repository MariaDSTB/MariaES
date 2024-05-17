package io.hansan.mariaes.auth.configuration;

import io.hansan.mariaes.auth.service.JwtTokenUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 17:41
 * @description：TODO
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .disable()
                .authorizeRequests()
                .antMatchers("/refreshToken", "/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
