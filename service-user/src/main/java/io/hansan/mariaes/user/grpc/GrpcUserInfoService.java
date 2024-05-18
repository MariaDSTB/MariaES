package io.hansan.mariaes.user.grpc;
import io.hansan.mariaes.lib.proto.user.GetUserRequest;
import io.hansan.mariaes.lib.proto.user.GetUserResponse;
import io.hansan.mariaes.lib.proto.user.UserInfoGrpc;
import io.grpc.stub.StreamObserver;
import io.hansan.mariaes.user.Service.UserService;
import io.hansan.mariaes.user.data.vo.UserVo;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.validation.annotation.Validated;

@GrpcService
@RequiredArgsConstructor
@Validated
public class GrpcUserInfoService extends UserInfoGrpc.UserInfoImplBase {
    private final UserService userService;
    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        // 这里实现获取用户的逻辑
        // 假设我们有一个方法可以根据request获取用户信息
        UserVo user = getUserFromDatabase(request.getUserId());

        // 创建响应
        GetUserResponse response = GetUserResponse.newBuilder()
                .setUserId(user.studentId())
                .setName(user.name())
            .build();

        // 发送响应
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public UserVo getUserFromDatabase(Long userId) {
        return userService.getUserById(userId);
    }
}