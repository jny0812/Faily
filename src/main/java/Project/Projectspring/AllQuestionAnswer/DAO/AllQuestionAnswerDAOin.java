package Project.Projectspring.AllQuestionAnswer.DAO;

import Project.Projectspring.AllQuestionAnswer.VO.*;

import java.util.List;

public interface AllQuestionAnswerDAOin {

    List<AllAnswerImageVO> getAnswer (AnswerNeedVO answerNeedVO) throws Exception;

    List<QuestionListVO> getQuestion(int group_id) throws Exception;
}
