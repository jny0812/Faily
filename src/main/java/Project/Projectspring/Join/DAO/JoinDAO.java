package Project.Projectspring.Join.DAO;

import Project.Projectspring.Join.VO.JoinVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinDAO implements JoinDAOin{

    private static final String NAMESPACE = "com.Project.Join.JoinMapper";

    private final SqlSession sqlSession;

    @Autowired
    public JoinDAO(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    public void create(JoinVO joinVO) throws Exception {
        sqlSession.insert(NAMESPACE+".create",joinVO);
    }

    @Override
    public String loginCheck(JoinVO joinVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".loginCheck",joinVO);
    }
}
