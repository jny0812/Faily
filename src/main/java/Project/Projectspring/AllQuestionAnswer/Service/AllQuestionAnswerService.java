package Project.Projectspring.AllQuestionAnswer.Service;

import Project.Projectspring.AllQuestionAnswer.DAO.AllQuestionAnswerDAO;
import Project.Projectspring.AllQuestionAnswer.VO.AllAnswerVO;
import Project.Projectspring.AllQuestionAnswer.VO.AllQuestionVO;
import Project.Projectspring.AllQuestionAnswer.VO.AnswerNeedVO;
import Project.Projectspring.AllQuestionAnswer.VO.QuestionListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllQuestionAnswerService implements AllQuestionAnswerServicein{

    private final AllQuestionAnswerDAO allQuestionAnswerDAO;

    @Override
    public List<AllAnswerVO> getAnswer(AnswerNeedVO answerNeedVO) throws Exception {
        return allQuestionAnswerDAO.getAnswer(answerNeedVO);
    }

    @Override
    public List<QuestionListVO> getQuestion(int group_id) throws Exception {
        return allQuestionAnswerDAO.getQuestion(group_id);
    }
}

