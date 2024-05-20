package io.hansan.mariaes.auth.service;

import io.hansan.mariaes.auth.data.dto.UserBaseSignInDto;
import io.hansan.mariaes.lib.proto.user.CheckPasswordRequest;
import io.hansan.mariaes.lib.proto.user.UserAuthenticationGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author ：何汉叁
 * @date ：2024/5/18 17:12
 * @description：TODO
 */
@Service
@Validated
public class AuthorizationValidationService {
    @GrpcClient("service-user")
    private UserAuthenticationGrpc.UserAuthenticationBlockingStub userAuthenticationBlockingStub;

    public Long validateUser(UserBaseSignInDto userSignInDto) {
        var checkPasswordResponse = userAuthenticationBlockingStub.checkPassword(CheckPasswordRequest.newBuilder()
                .setName(userSignInDto.username())
                .setPassword(userSignInDto.password())
                .build());
        if (! checkPasswordResponse.getIsCorrect()) {
       throw new RuntimeException("用户名或密码错误");
        }
        return checkPasswordResponse.getUserId();
    }
}
