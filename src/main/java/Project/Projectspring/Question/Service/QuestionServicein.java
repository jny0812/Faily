package Project.Projectspring.Question.Service;


import Project.Projectspring.Question.VO.AllQuestionVO;

import java.util.List;

public interface QuestionServicein {

    String questionNumberCheck(int question_number) throws Exception;

    int userIdCheck(String e_mail) throws Exception;

    void statusChangeToZero(int user_id) throws Exception;

    int questionId(String question) throws Exception;

    int questionUserGroupId(int user_id) throws Exception;

    void createGroupQuestion(AllQuestionVO allQuestionVO) throws Exception;

    List<AllQuestionVO> allQuestion(int group_id) throws Exception;
}
