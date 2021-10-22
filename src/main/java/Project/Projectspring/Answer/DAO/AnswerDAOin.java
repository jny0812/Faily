package Project.Projectspring.Answer.DAO;

import Project.Projectspring.Answer.VO.AnswerUpdateVO;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Answer.VO.CheckAnswerVO;

public interface AnswerDAOin {

    void createAnswer(AnswerVO answerVO) throws Exception;

    void updateAnswerUserId(AnswerUpdateVO answerUpdateVO) throws Exception;

    void statusChangeToOne(int user_id) throws Exception;

    int bringGroupQuestionId(int group_id) throws Exception;

    int checkAnswer(CheckAnswerVO checkAnswerVO) throws Exception;
}
