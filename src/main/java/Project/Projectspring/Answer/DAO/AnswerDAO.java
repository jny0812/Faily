package Project.Projectspring.Answer.DAO;

import Project.Projectspring.Answer.VO.AnswerUpdateVO;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Answer.VO.CheckAnswerVO;
import com.rabbitmq.client.AMQP;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDAO implements AnswerDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Answer.AnswerMapper";

    private final SqlSession sqlSession;

    @Autowired
    public AnswerDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public void createAnswer(AnswerVO answerVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createAnswer",answerVO);
    }

    @Override
    public void updateAnswerUserId(AnswerUpdateVO answerUpdateVO) throws Exception {
        sqlSession.update(NAMESPACE+".updateAnswerUserId", answerUpdateVO);
    }

    @Override
    public void statusChangeToOne(int user_id) throws Exception {
        sqlSession.update(NAMESPACE+".statusChangeToOne",user_id);
    }

    @Override
    public int bringGroupQuestionId(int group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".bringGroupQuestionId",group_id);
    }

    @Override
    public int checkAnswer(CheckAnswerVO checkAnswerVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".checkAnswer",checkAnswerVO);
    }
}
