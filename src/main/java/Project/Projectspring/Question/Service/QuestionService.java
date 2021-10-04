package Project.Projectspring.Question.Service;

import Project.Projectspring.Question.DAO.QuestionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
