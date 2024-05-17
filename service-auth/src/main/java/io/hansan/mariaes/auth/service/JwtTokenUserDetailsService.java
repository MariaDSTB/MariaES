package io.hansan.mariaes.auth.service;

import io.hansan.mariaes.auth.database.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ：何汉叁
 * @date ：2024/5/17 17:58
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class JwtTokenUserDetailsService implements UserDetailsService{
    private final UserDetailRepository userDetailRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
    }

}
