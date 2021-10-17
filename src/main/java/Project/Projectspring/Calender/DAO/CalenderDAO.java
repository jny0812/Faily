package Project.Projectspring.Calender.DAO;

import Project.Projectspring.Calender.VO.AllCalendarVO;
import Project.Projectspring.Calender.VO.CalendarListVO;
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

    @Override
    public void addCalendar(CalenderVO calenderVO) throws Exception {
        sqlSession.insert(NAMESPACE+".addCalendar", calenderVO);
    }

    @Override
    public List<AllCalendarVO> CalendarList(CalendarListVO calendarListVO) throws Exception {
        return sqlSession.selectList(NAMESPACE+".CalendarList", calendarListVO);
    }

}
