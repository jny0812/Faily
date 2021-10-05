package Project.Projectspring.Question.DAO;

import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Question.VO.AllQuestionVO;
import Project.Projectspring.Question.VO.QuestionVO;

import java.util.HashMap;
import java.util.List;

public interface QuestionDAOin {

    String questionNumberCheck(int question_number) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void statusChangeToZero(int user_id) throws Exception;

    int questionId(String question) throws Exception;

    int questionUserGroupId(int user_id) throws Exception;

    void createGroupQuestion(AllQuestionVO allQuestionVO) throws Exception;

    List<AllQuestionVO> allQuestion(int group_id) throws Exception;

}
