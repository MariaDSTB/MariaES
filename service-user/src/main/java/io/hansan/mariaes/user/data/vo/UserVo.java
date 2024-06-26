package io.hansan.mariaes.user.data.vo;

import com.sun.istack.NotNull;
import io.hansan.mariaes.user.database.entity.UserEntity;

/**
 * @author ：何汉叁
 * @date ：2024/4/25 18:25
 * @description：TODO
 */
public record UserVo (
        Long userId,
        String name
){
    @NotNull
    public static UserVo fromUserEntity(UserEntity userEntity){
        return new UserVo(userEntity.getId(), userEntity.getName());
    }
}
