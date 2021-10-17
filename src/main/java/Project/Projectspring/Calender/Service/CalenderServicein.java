package Project.Projectspring.Calender.Service;

import Project.Projectspring.Calender.VO.AllCalendarVO;
import Project.Projectspring.Calender.VO.CalendarListVO;
import Project.Projectspring.Calender.VO.CalenderVO;

import java.util.HashMap;
import java.util.List;

public interface CalenderServicein {

    List<CalenderVO> Calender (int group_id) throws Exception;

    void addCalendar(CalenderVO calenderVO) throws Exception;

    List<AllCalendarVO> CalendarList(CalendarListVO calendarListVO) throws Exception;

}
