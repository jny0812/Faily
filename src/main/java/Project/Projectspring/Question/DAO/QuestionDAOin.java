package Project.Projectspring.Question.DAO;

import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Question.VO.QuestionVO;

public interface QuestionDAOin {

    String questionNumberCheck(int question_number) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void statusChangeToZero(int user_id) throws Exception;

}
