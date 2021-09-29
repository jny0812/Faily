package Project.Projectspring.Group.DAO;


import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Join.VO.JoinVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAO implements GroupDAOin{

    private static final String NAMESPACE = "com.Project.Group.GroupMapper";

    private final SqlSession sqlSession;

    @Autowired
    public GroupDAO(SqlSession sqlSession) { this.sqlSession = sqlSession;}

    @Override
    public void createGroup(GroupVO groupVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createGroup",groupVO);
    }

    @Override
    public void createCode(GroupVO groupVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createCode",groupVO);
    }

    @Override
    public String codeCheck(GroupVO groupVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".codeCheck",groupVO);
    }

}
