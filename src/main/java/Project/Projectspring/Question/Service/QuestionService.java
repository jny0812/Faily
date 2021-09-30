package Project.Projectspring.Question.Service;

import Project.Projectspring.Question.DAO.QuestionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionServicein{

    private final QuestionDAO questionDAO;

//    @Autowired
//    public QuestionService(QuestionDAO questionDAO) {
//        this.questionDAO = questionDAO;
//    }

    @Override
    public String questionNumberCheck(int question_number) throws Exception {
        return QuestionDAO.questionNumberCheck(question_number);
    }
}
