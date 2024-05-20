package io.hansan.mariaes.user.grpc;

import io.grpc.stub.StreamObserver;
import io.hansan.mariaes.lib.proto.user.RegisterUserRequest;
import io.hansan.mariaes.lib.proto.user.RegisterUserResponse;
import io.hansan.mariaes.lib.proto.user.UserRegistrationGrpc;
import io.hansan.mariaes.user.Service.UserRegistrationService;
import io.hansan.mariaes.user.data.vo.UserRegistrationDto;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.validation.annotation.Validated;

/**
 * @author ：何汉叁
 * @date ：2024/5/19 11:05
 * @description：TODO
 */
@GrpcService
@RequiredArgsConstructor
@Validated
public class GrpcUserRegistrationService extends UserRegistrationGrpc.UserRegistrationImplBase{
    private final UserRegistrationService userRegistrationService;
    @Override
    public void registerUser(RegisterUserRequest request, StreamObserver<RegisterUserResponse> responseObserver) {
        var userId = userRegistrationService.registerUser(new UserRegistrationDto(request.getName(), request.getPassword()));
        var response = RegisterUserResponse.newBuilder()
                .setUserId(userId)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
