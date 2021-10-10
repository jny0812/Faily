package Project.Projectspring.Answer.Service;

import Project.Projectspring.Answer.DAO.AnswerDAO;
import Project.Projectspring.Answer.DAO.IsAnsweredDAO;
import Project.Projectspring.Answer.VO.IsAnsweredVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
@RequiredArgsConstructor
public class IsAnsweredService implements IsAnsweredServicein{

    private final IsAnsweredDAO isAnsweredDAO;

    @Override
    public int checkUserStatus(String e_mail) throws Exception {
        return isAnsweredDAO.checkUserStatus(e_mail);
    }

    @Override
    public int answeredUserGroupId(String e_mail) throws Exception {
        return isAnsweredDAO.answeredUserGroupId(e_mail);
    }

    @Override
    public int answeredUserNumber(int user_group_id) throws Exception {
        return isAnsweredDAO.answeredUserNumber(user_group_id);
    }

    @Override
    public int userNumber(int user_group_id) throws Exception {
        return isAnsweredDAO.userNumber(user_group_id);
    }

    @Override
    public String userName(String e_mail) throws Exception {
        return isAnsweredDAO.userName(e_mail);
    }

    @Override
    public Integer isAnsweredUser(int question_id) throws Exception {
        return isAnsweredDAO.isAnsweredUser(question_id);
    }
}
