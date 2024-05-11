package io.hansan.grade.service;

import io.hansan.grade.data.vo.GradeVo;
import io.hansan.grade.database.Entity.GradeEntity;
import io.hansan.grade.database.repository.GradeRepository;
import io.hansan.mariaes.lib.proto.exam.ExamInfoGrpc;
import io.hansan.mariaes.lib.proto.exam.ExamRequest;
import io.hansan.mariaes.lib.proto.exam.ExamResponse;
import io.hansan.mariaes.lib.proto.user.GetUserRequest;
import io.hansan.mariaes.lib.proto.user.GetUserResponse;
import io.hansan.mariaes.lib.proto.user.UserInfoGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ：何汉叁
 * @date ：2024/5/2 23:20
 * @description：TODO
 */
@Service
@RequiredArgsConstructor
public class GradeService {
    public final GradeRepository gradeRepository;
    @GrpcClient("GrpcExamInfoService")
    private ExamInfoGrpc.ExamInfoBlockingStub examInfoBlockingStub;
    @GrpcClient("GrpcUserInfoService")
    private UserInfoGrpc.UserInfoBlockingStub userInfoBlockingStub;

    public GradeVo getGrade(Long id)  {
        Optional<GradeEntity> gradeEntityOptional = gradeRepository.findById(id);
        if (gradeEntityOptional.isPresent()) {
            GradeEntity gradeEntity = gradeEntityOptional.get();
            /**
             * 通过grpc调用exam服务获取考试信息
             */
            ExamRequest examRequest = ExamRequest.newBuilder().setExamId(gradeEntity.getExamId()).build();
            ExamResponse examInfo = examInfoBlockingStub.getExamInfo(examRequest);
            /**
             * 通过grpc调用user服务获取用户信息
             */
            GetUserRequest userRequest = GetUserRequest.newBuilder().setUserId(gradeEntity.getStudentId()).build();
            GetUserResponse user = userInfoBlockingStub.getUser(userRequest);
            return GradeVo.fromEntity(examInfo.getExamName(), gradeEntity.getScore(), user.getName());
        }else
            return null;
    }
}
