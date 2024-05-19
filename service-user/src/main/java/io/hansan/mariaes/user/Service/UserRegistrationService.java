package io.hansan.mariaes.user.Service;

import io.hansan.mariaes.user.data.vo.UserRegistrationDto;
import io.hansan.mariaes.user.database.entity.UserEntity;
import io.hansan.mariaes.user.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ：何汉叁
 * @date ：2024/5/19 10:45
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final UserRepository userRepository;
    public Long registerUser(UserRegistrationDto baseUserRegistrationDto) {
        UserEntity save = userRepository.save(new UserEntity().fromBaseUserRegistrationDto(baseUserRegistrationDto));
        return save.getId();
    }
}
