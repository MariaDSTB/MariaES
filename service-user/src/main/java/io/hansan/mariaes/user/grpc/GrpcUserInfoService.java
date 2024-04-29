package io.hansan.mariaes.user.grpc;
import io.hansan.mariaes.lib.proto.user.GetUserRequest;
import io.hansan.mariaes.lib.proto.user.GetUserResponse;
import io.hansan.mariaes.lib.proto.user.UserInfoGrpc;
import io.grpc.stub.StreamObserver;
import io.hansan.mariaes.user.data.vo.UserVo;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class GrpcUserInfoService extends UserInfoGrpc.UserInfoImplBase {

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        // 这里实现获取用户的逻辑
        // 假设我们有一个方法可以根据request获取用户信息
        UserVo user = getUserFromDatabase(Long.parseLong(request.getUserId()));

        // 创建响应
        GetUserResponse response = GetUserResponse.newBuilder()
                .setUserId(user.studentid())
                .setUserName(user.name())
            .build();

        // 发送响应
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public UserVo getUserFromDatabase(Long userId) {
        // 这里实现从数据库获取用户的逻辑
        // 这只是一个示例，实际情况可能会有所不同
        return new UserVo(userId, "testName", "testEmail");
    }
}