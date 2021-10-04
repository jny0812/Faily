package Project.Projectspring.Answer.DAO;

import Project.Projectspring.Answer.VO.AnswerVO;

import java.util.Calendar;

public interface AnswerDAOin {

    void createAnswer(AnswerVO answerVO) throws Exception;

    void updateAnswerUserId(int user_id) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void updateAnswerTime(AnswerVO answerVO) throws Exception;

    int checkAnswerId(AnswerVO answerVO) throws Exception;

    void statusChangeToOne(int user_id) throws Exception;
}
