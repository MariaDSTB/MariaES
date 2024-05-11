package io.hansan.exam.grpc;

import io.grpc.stub.StreamObserver;
import io.hansan.exam.data.vo.ExamVo;
import io.hansan.exam.service.ExamService;
import io.hansan.mariaes.lib.proto.exam.ExamInfoGrpc;
import io.hansan.mariaes.lib.proto.exam.ExamRequest;
import io.hansan.mariaes.lib.proto.exam.ExamResponse;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.validation.annotation.Validated;

/**
 * @author ：何汉叁
 * @date ：2024/5/11 22:25
 * @description：TODO
 */
@GrpcService
@RequiredArgsConstructor
@Validated
public class GrpcExamInfoService extends ExamInfoGrpc.ExamInfoImplBase{
    public final ExamService examService;

    @Override
    public void getExamInfo(ExamRequest request, StreamObserver<ExamResponse> responseObserver) {
        ExamVo examVo = examService.getExam(request.getExamId());
        ExamResponse response = ExamResponse.newBuilder()
                .setPageId(examVo.pageId())
                .setExamName(examVo.name())
                .setExamDesc(examVo.description())
                .setExamTime(examVo.startTime())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
