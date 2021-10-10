package Project.Projectspring.Question.DAO;

import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Question.VO.AllQuestionVO;
import Project.Projectspring.Question.VO.GroupQuestionVO;
import Project.Projectspring.Question.VO.QuestionVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QuestionDAOin {

    String questionNumberCheck(int question_number) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void statusChangeToZero(int user_id) throws Exception;

    int questionId(String question) throws Exception;

    int questionUserGroupId(int user_id) throws Exception;

    void createGroupQuestion(GroupQuestionVO groupQuestionVO) throws Exception;

    List<AllQuestionVO> allQuestion(int group_id) throws Exception;

    String bringQuestion(int group_id) throws Exception;

    void ChangeGroupQuestion(int group_id) throws Exception;

    String questionTime(int group_id) throws Exception;

    List<AllQuestionVO> GroupquestionAnswer(int question_id) throws Exception;

    List<AllQuestionVO> UserAnswer(int answer_group_id) throws Exception;

    List<Map<String,Object>> selectQuestions(int group_id) throws Exception;

    int isAnsweredUser(int question_id) throws Exception;

}
