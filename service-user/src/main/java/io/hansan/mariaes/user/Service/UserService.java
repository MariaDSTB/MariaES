package io.hansan.mariaes.user.Service;

import io.hansan.mariaes.user.data.vo.UserVo;
import io.hansan.mariaes.user.database.entity.UserEntity;
import io.hansan.mariaes.user.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:16
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserVo getUserById(Long id) {
        return UserVo.fromUserEntity(userRepository.findById(id).orElseThrow());
    }

    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public List<UserVo> getAllUsers(int page, int size){
        return userRepository.findAll(PageRequest.of(page, size, Sort.by("createdAt").descending())).stream()
                .map(UserVo::fromUserEntity)
                .toList();
    }
}
