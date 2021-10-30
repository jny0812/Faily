package Project.Projectspring.Home.DAO;

import Project.Projectspring.Home.Controller.HomeApiController;
import Project.Projectspring.Home.VO.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class HomeDAO implements HomeDAOin{

    private static final String NAMESPACE = "Project.Projectspring.Home.HomeMapper";

    private final SqlSession sqlSession;

    @Autowired
    public HomeDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public List<GetByGroupIdVO> getByGroupId(HomeApiController.FindNeedVO findNeedVO) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getByGroupId", findNeedVO);
    }

    @Override
    public String getUserMood(int user_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".getUserMood", user_id);
    }

    @Override
    public List<FamilyListNeedVO> getFamilyList(int group_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getFamilyList",group_id);
    }

    @Override
    public String getUserName(int user_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".getUserName",user_id);
    }

    @Override
    public List<TodayAnniversaryVO> getCalendar(HomeApiController.FindNeedVO findNeedVO) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getCalendar",findNeedVO);
    }

    @Override
    public List<ImagePathVO> getUserImagePath(int user_id) throws Exception {
        return sqlSession.selectList(NAMESPACE+".getUserImagePath", user_id);
    }

    @Override
    public void putImagePath(HomeApiController.putImageVO putImageVO) throws Exception {
        sqlSession.update(NAMESPACE+".putImagePath",putImageVO);
    }

    @Override
    public String CheckCalendar(String calendar_date) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".CheckCalendar", calendar_date);
    }

    @Override
    public float GroupBonding(int group_id) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".GroupBonding",group_id);
    }


}
