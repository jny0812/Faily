package Project.Projectspring.Answer.DAO;

import Project.Projectspring.Answer.VO.IsAnsweredVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.MediaSize;

@Repository
public class IsAnsweredDAO implements IsAnsweredDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Answer.AnswerMapper";

    private final SqlSession sqlSession;

    @Autowired
    public IsAnsweredDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public int checkUserStatus(String e_mail) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".checkUserStatus",e_mail);
    }

    @Override
    public int answeredUserGroupId(String e_mail) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".answeredUserGroupId", e_mail);
    }

    @Override
    public int answeredUserNumber(int user_group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".answeredUserNumber", user_group_id);
    }

    @Override
    public int userNumber(int user_group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".userNumber", user_group_id);
    }

    @Override
    public String userName(String e_mail) throws Exception {
        return sqlSession.selectOne(NAMESPACE+"userName", e_mail);
    }

    @Override
    public Integer isAnsweredUser(int question_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+"isAnsweredUser", question_id);
    }

}
