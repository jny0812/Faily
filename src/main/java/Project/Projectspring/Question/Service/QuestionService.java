package Project.Projectspring.Question.Service;

import Project.Projectspring.Question.DAO.QuestionDAO;
import Project.Projectspring.Question.VO.AllQuestionVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void createGroupQuestion(AllQuestionVO allQuestionVO) throws Exception {
        questionDAO.createGroupQuestion(allQuestionVO);
    }

    @Override
    public List<AllQuestionVO> allQuestion(int group_id) throws Exception {
        return questionDAO.allQuestion(group_id);
    }
}
