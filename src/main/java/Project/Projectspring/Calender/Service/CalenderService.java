package Project.Projectspring.Calender.Service;

import Project.Projectspring.Calender.DAO.CalenderDAO;
import Project.Projectspring.Calender.VO.AllCalendarVO;
import Project.Projectspring.Calender.VO.CalendarListVO;
import Project.Projectspring.Calender.VO.CalenderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService implements CalenderServicein{

    private final CalenderDAO calenderDAO;


    @Override
    public List<CalenderVO> Calender(int group_id) throws Exception {
        return calenderDAO.Calender(group_id);
    }

    @Override
    public void addCalendar(CalenderVO calenderVO) throws Exception{
        calenderDAO.addCalendar(calenderVO);
    }

    @Override
    public List<AllCalendarVO> CalendarList(CalendarListVO calendarListVO) throws Exception {
        return calenderDAO.CalendarList(calendarListVO);
    }
}
