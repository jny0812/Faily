package Project.Projectspring.Question.Service;

import Project.Projectspring.Question.DAO.QuestionDAO;
import Project.Projectspring.Question.VO.AllQuestionsVO;
import Project.Projectspring.Question.VO.GroupQuestionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionServicein{

    private final QuestionDAO questionDAO;


    @Override
    public String questionNumberCheck(int question_number) throws Exception {
       return questionDAO.questionNumberCheck(question_number);
    }

    @Override
    public int userIdCheck(String e_mail) throws Exception {
        return questionDAO.userIdCheck(e_mail);
    }

    @Override
    public void statusChangeToZero(int user_id) throws Exception {
        questionDAO.statusChangeToZero(user_id);
    }

    @Override
    public int questionId(String question) throws Exception {
        return questionDAO.questionId(question);
    }

    @Override
    public int questionUserGroupId(int user_id) throws Exception {
        return questionDAO.questionUserGroupId(user_id);
    }

    @Override
    public void createGroupQuestion(GroupQuestionVO groupQuestionVO) throws Exception {
        questionDAO.createGroupQuestion(groupQuestionVO);
    }

    @Override
    public List<AllQuestionsVO> allQuestion(int group_id) throws Exception {
        return questionDAO.allQuestion(group_id);
    }

    @Override
    public String bringQuestion(int group_id) throws Exception {
        return questionDAO.bringQuestion(group_id);
    }

    @Override
    public void ChangeGroupQuestion(int group_id) throws Exception {
        questionDAO.ChangeGroupQuestion(group_id);
    }

    @Override
    public String questionTime(int group_id) throws Exception {
        return null;
    }

    @Override
    public List<AllQuestionsVO> GroupquestionAnswer(int question_id) throws Exception {
        return questionDAO.GroupquestionAnswer(question_id);
    }

    @Override
    public List<AllQuestionsVO> UserAnswer(int answer_group_id) throws Exception {
        return questionDAO.UserAnswer(answer_group_id);
    }

    @Override
    public List<Map<String, Object>> selectQuestions(int group_id) throws Exception {
        return questionDAO.selectQuestions(group_id);
    }

}
