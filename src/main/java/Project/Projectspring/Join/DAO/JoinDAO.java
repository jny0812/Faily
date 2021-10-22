package Project.Projectspring.Join.DAO;

import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Join.VO.JwtTokenVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JoinDAO implements JoinDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Join.JoinMapper";

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

    @Override
    public String passwordCheck(JoinVO joinVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".passwordCheck",joinVO);
    }

    @Override
    public void updateJwtToken(JwtTokenVO jwtTokenVO) throws Exception {
        sqlSession.update(NAMESPACE+".updateJwtToken",jwtTokenVO);
    }
}
