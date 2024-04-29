import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import io.hansan.mariaes.lib.proto.user.GetUserRequest;
import io.hansan.mariaes.lib.proto.user.GetUserResponse;
import io.hansan.mariaes.lib.proto.user.UserInfoGrpc;
import io.hansan.mariaes.user.data.vo.UserVo;
import io.hansan.mariaes.user.grpc.GrpcUserInfoService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GrpcUserInfoServiceTest {
    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Test
    public void returnsCorrectResponseWhenUserExists() throws Exception {
        GrpcUserInfoService service = Mockito.spy(new GrpcUserInfoService());
        when(service.getUserFromDatabase(any(Long.class))).thenReturn(new UserVo(1L, "testName", "testClass"));

        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(service).build().start());

        UserInfoGrpc.UserInfoBlockingStub stub = UserInfoGrpc.newBlockingStub(
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        GetUserRequest request = GetUserRequest.newBuilder()
                .setUserId("1")
                .build();

        GetUserResponse response = stub.getUser(request);
        System.out.println(response);
//        assertEquals("1", response.getUserId());
//        assertEquals("testName", response.getUserName());
    }

    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenDatabaseCallFails() throws Exception {
        GrpcUserInfoService service = Mockito.spy(new GrpcUserInfoService());
        when(service.getUserFromDatabase(any(Long.class))).thenThrow(new RuntimeException("Database error"));

        String serverName = InProcessServerBuilder.generateName();
        grpcCleanup.register(InProcessServerBuilder
                .forName(serverName).directExecutor().addService(service).build().start());

        UserInfoGrpc.UserInfoBlockingStub stub = UserInfoGrpc.newBlockingStub(
                grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

        GetUserRequest request = GetUserRequest.newBuilder()
                .setUserId("1")
                .build();

        stub.getUser(request);
    }
}