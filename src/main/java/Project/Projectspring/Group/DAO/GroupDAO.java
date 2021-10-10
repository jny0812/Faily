package Project.Projectspring.Group.DAO;


import Project.Projectspring.Group.VO.GroupCreateTimeVO;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAO implements GroupDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Group.GroupMapper";

    private final SqlSession sqlSession;

    @Autowired
    public GroupDAO(SqlSession sqlSession) { this.sqlSession = sqlSession;}

    @Override
    public void createGroup(GroupCreateTimeVO groupCreateTimeVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createGroup",groupCreateTimeVO);
    }

    @Override
    public String codeCheck(GroupVO groupVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".codeCheck",groupVO);
    }

    @Override
    public int groupIdCheck(String group_code) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".groupIdCheck",group_code);
    }

    @Override
    public void updateUserGroupId( UserGroupVO userGroupVO) throws Exception {
         sqlSession.update(NAMESPACE+".updateUserGroupId",userGroupVO);
    }

    @Override
    public Integer isExisted(String e_mail) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".isExisted",e_mail);
    }

    @Override
    public String groupCode(int group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".groupCode",group_id);
    }


}
