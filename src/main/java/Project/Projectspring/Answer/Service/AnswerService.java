package Project.Projectspring.Answer.Service;

import Project.Projectspring.Answer.DAO.AnswerDAO;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Group.DAO.GroupDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class AnswerService implements AnswerServicein{

    private final AnswerDAO answerDAO;

    @Override
    public void createAnswer(AnswerVO answerVO) throws Exception {
        answerDAO.createAnswer(answerVO);
    }

    @Override
    public void updateAnswerUserId(int user_id) throws Exception {
        answerDAO.updateAnswerUserId(user_id);
    }

    @Override
    public int userIdCheck(String e_mail) throws Exception {
        return answerDAO.userIdCheck(e_mail);
    }

    @Override
    public void updateAnswerTime(AnswerVO answerVO) throws Exception {
        answerDAO.updateAnswerTime(answerVO);
    }

    @Override
    public int checkAnswerId(AnswerVO answerVO) throws Exception {
        return answerDAO.checkAnswerId(answerVO);
    }

    @Override
    public void statusChangeToOne(int user_id) throws Exception {
        answerDAO.statusChangeToOne(user_id);
    }
}
