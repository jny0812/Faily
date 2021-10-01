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
}
