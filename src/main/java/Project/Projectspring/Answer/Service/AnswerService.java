package Project.Projectspring.Answer.Service;

import Project.Projectspring.Answer.DAO.AnswerDAO;
import Project.Projectspring.Answer.VO.AnswerUpdateVO;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Answer.VO.CheckAnswerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswerServicein{

    private final AnswerDAO answerDAO;

    @Override
    public void createAnswer(AnswerVO answerVO) throws Exception {
        answerDAO.createAnswer(answerVO);
    }

    @Override
    public void updateAnswerUserId(AnswerUpdateVO answerUpdateVO) throws Exception {
        answerDAO.updateAnswerUserId(answerUpdateVO);
    }

    @Override
    public void statusChangeToOne(int user_id) throws Exception {
        answerDAO.statusChangeToOne(user_id);
    }

    @Override
    public int bringGroupQuestionId(int group_id) throws Exception {
        return answerDAO.bringGroupQuestionId(group_id);
    }

    @Override
    public int checkAnswer(CheckAnswerVO checkAnswerVO) throws Exception {
        return answerDAO.checkAnswer(checkAnswerVO);
    }

    @Override
    public void updateUserBonding(int user_id) throws Exception {
        answerDAO.updateUserBonding(user_id);
    }
}
