package Project.Projectspring.Answer.Service;

import Project.Projectspring.Answer.VO.AnswerUpdateVO;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Answer.VO.CheckAnswerVO;

public interface AnswerServicein {

    void createAnswer(AnswerVO answerVO) throws Exception;

    void updateAnswerUserId(AnswerUpdateVO answerUpdateVO) throws Exception;

    void statusChangeToOne(int user_id) throws Exception;

    int bringGroupQuestionId(int group_id) throws Exception;

    int checkAnswer(CheckAnswerVO checkAnswerVO) throws Exception;

    void updateUserBonding(int user_id) throws Exception;
}
