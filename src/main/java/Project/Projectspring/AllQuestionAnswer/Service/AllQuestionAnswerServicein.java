package Project.Projectspring.AllQuestionAnswer.Service;

import Project.Projectspring.AllQuestionAnswer.VO.*;

import java.util.List;

public interface AllQuestionAnswerServicein {

    List<AllAnswerImageVO> getAnswer (AnswerNeedVO answerNeedVO) throws Exception;

    List<QuestionListVO> getQuestion(int group_id) throws Exception;
}
