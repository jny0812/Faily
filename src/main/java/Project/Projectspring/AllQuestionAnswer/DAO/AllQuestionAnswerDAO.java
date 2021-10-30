package Project.Projectspring.AllQuestionAnswer.DAO;

import Project.Projectspring.AllQuestionAnswer.VO.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AllQuestionAnswerDAO implements AllQuestionAnswerDAOin{

    private static final String NAMESPACE = "Project.Projectspring.AllQuestionAnswer.AllQuestionAnswerMapper";

    private final SqlSession sqlSession;

    @Autowired
    public AllQuestionAnswerDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}


    @Override
    public List<AllAnswerImageVO> getAnswer(AnswerNeedVO answerNeedVO) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getAnswer", answerNeedVO);
    }

    @Override
    public List<QuestionListVO> getQuestion(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getQuestion",group_id);
    }
}
