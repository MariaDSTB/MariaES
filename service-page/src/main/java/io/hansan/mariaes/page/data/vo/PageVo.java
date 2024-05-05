package io.hansan.mariaes.page.data.vo;
import com.sun.istack.NotNull;
import io.hansan.mariaes.page.database.entity.PageEntity;
import io.hansan.mariaes.page.database.entity.QuestionPageEntity;
import io.hansan.mariaes.question.data.vo.QuestionVo;

import java.util.Date;
import java.util.List;

/**
 * @author ：何汉叁
 * @date ：2024/4/29 8:36
 * @description：TODO
 */
public record PageVo(
        String title,
        Date createTime,
        List<QuestionPageEntity> QuestionPageEntity
){
    public static PageVo FromQuestionPage(@NotNull PageEntity pageEntity, List<QuestionPageEntity> questionPageEntities) {
        return new PageVo(pageEntity.getTitle(), pageEntity.getCreateAt(), questionPageEntities);
    }
}
