package Project.Projectspring.AllQuestionAnswer.DAO;

import Project.Projectspring.AllQuestionAnswer.VO.AllAnswerVO;
import Project.Projectspring.AllQuestionAnswer.VO.AllQuestionVO;
import Project.Projectspring.AllQuestionAnswer.VO.AnswerNeedVO;

import java.util.List;

public interface AllQuestionAnswerDAOin {

    List<AllAnswerVO> getAnswer (AnswerNeedVO answerNeedVO) throws Exception;

    List<AllQuestionVO> getQuestion(int group_id) throws Exception;
}
