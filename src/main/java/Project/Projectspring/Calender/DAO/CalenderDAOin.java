package Project.Projectspring.Calender.DAO;

import Project.Projectspring.Calender.VO.CalenderVO;

import java.util.HashMap;
import java.util.List;

public interface CalenderDAOin {

   List<CalenderVO>Calender (int group_id) throws Exception;
}
