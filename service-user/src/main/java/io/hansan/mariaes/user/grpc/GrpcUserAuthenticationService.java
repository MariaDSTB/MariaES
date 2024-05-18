package io.hansan.mariaes.user.grpc;

import io.grpc.stub.StreamObserver;
import io.hansan.mariaes.lib.proto.user.CheckPasswordRequest;
import io.hansan.mariaes.lib.proto.user.CheckPasswordResponse;
import io.hansan.mariaes.lib.proto.user.UserAuthenticationGrpc;
import io.hansan.mariaes.user.database.entity.UserEntity;
import io.hansan.mariaes.user.database.repository.UserRepository;
import io.hansan.mariaes.user.exception.UserInfoMissMatchException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * @author ：何汉叁
 * @date ：2024/5/18 16:34
 * @description：TODO
 */
@GrpcService
@RequiredArgsConstructor
@Validated
public class GrpcUserAuthenticationService extends UserAuthenticationGrpc.UserAuthenticationImplBase {
    private final UserRepository userRepository;

@Override
public void checkPassword(CheckPasswordRequest request, StreamObserver<CheckPasswordResponse> responseObserver) {
    UserEntity user = userRepository.findByName(request.getName()).orElse(null);
    var isCorrect = false;
    var userId = 0L;
    if (user != null) {
        isCorrect = user.getPassword().equals(request.getPassword());
        userId = user.getId();
    } else {
        responseObserver.onError(new UserInfoMissMatchException());
        return;
    }

    CheckPasswordResponse response = CheckPasswordResponse.newBuilder()
            .setIsCorrect(isCorrect)
            .setUserId(userId)
            .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
}
}
