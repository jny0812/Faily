package Project.Projectspring.Calender.DAO;

import Project.Projectspring.Calender.VO.CalenderVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class CalenderDAO implements CalenderDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Calender.CalenderMapper";

    private final SqlSession sqlSession;

    @Autowired
    public CalenderDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public List<CalenderVO> Calender(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".Calender", group_id);
    }

}
