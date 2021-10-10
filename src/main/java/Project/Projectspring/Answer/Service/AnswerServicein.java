package Project.Projectspring.Answer.Service;

import Project.Projectspring.Answer.VO.AnswerUpdateVO;
import Project.Projectspring.Answer.VO.AnswerVO;

public interface AnswerServicein {

    void createAnswer(AnswerVO answerVO) throws Exception;

    void updateAnswerUserId(AnswerUpdateVO answerUpdateVO) throws Exception;

    void statusChangeToOne(int user_id) throws Exception;

    int bringGroupQuestionId(int group_id) throws Exception;
}
