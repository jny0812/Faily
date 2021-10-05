package Project.Projectspring.Question.DAO;


import Project.Projectspring.Question.VO.AllQuestionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO implements QuestionDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Question.QuestionMapper";

    private final SqlSession sqlSession;

    @Autowired
    public QuestionDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public String questionNumberCheck(int question_number) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionNumberCheck",question_number);
    }

    @Override
    public int userIdCheck(String e_mail) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".userIdCheck",e_mail);
    }

    @Override
    public void statusChangeToZero(int user_id) throws Exception {
        sqlSession.update(NAMESPACE+".statusChangeToZero",user_id);
    }


    @Override
    public int questionId(String question) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionId",question);
    }

    @Override
    public int questionUserGroupId(int user_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".questionUserGroupId",user_id);
    }

    @Override
    public void createGroupQuestion(AllQuestionVO allQuestionVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createGroupQuestion", allQuestionVO);
    }

    @Override
    public List<Project.Projectspring.Question.VO.AllQuestionVO> allQuestion(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".allQuestion",group_id);
    }

}
