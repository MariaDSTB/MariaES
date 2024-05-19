package io.hansan.mariaes.auth.service;

import io.hansan.mariaes.auth.data.dto.UserBaseSignUpDto;
import io.hansan.mariaes.lib.proto.user.RegisterUserRequest;
import io.hansan.mariaes.lib.proto.user.UserRegistrationGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author ：何汉叁
 * @date ：2024/5/19 11:16
 * @description：TODO
 */
@Service
public class BaseRegistrationService {
    @GrpcClient("service-user")
    private UserRegistrationGrpc.UserRegistrationBlockingStub userRegistrationBlockingStub;
    public Long registerUser(UserBaseSignUpDto userBaseSignUpDto) {
        return userRegistrationBlockingStub.registerUser(RegisterUserRequest.newBuilder()
                .setName(userBaseSignUpDto.name())
                .setPassword(userBaseSignUpDto.password())
                .build()).getUserId();
    }
}
