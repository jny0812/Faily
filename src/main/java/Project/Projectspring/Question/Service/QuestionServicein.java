package Project.Projectspring.Question.Service;


import Project.Projectspring.Question.VO.AllQuestionsVO;
import Project.Projectspring.Question.VO.GroupQuestionVO;

import java.util.List;
import java.util.Map;

public interface QuestionServicein {

    String questionNumberCheck(int question_number) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void statusChangeToZero(int user_id) throws Exception;

    int questionId(String question) throws Exception;

    int questionUserGroupId(int user_id) throws Exception;

    void createGroupQuestion(GroupQuestionVO groupQuestionVO) throws Exception;

    List<AllQuestionsVO> allQuestion(int group_id) throws Exception;

    String bringQuestion(int group_id) throws Exception;

    void ChangeGroupQuestion(int group_id) throws Exception;


    List<AllQuestionsVO> GroupquestionAnswer(int question_id) throws Exception;

    List<AllQuestionsVO> UserAnswer(int answer_group_id) throws Exception;

    int groupQuestionId(int group_id) throws Exception;

    void updateGroupQuestionId() throws Exception;

    int CheckQuestionInfo(GroupQuestionVO groupQuestionVO) throws Exception;


}
