package Project.Projectspring.Home.Service;

import Project.Projectspring.Home.Controller.HomeApiController;
import Project.Projectspring.Home.DAO.HomeDAO;
import Project.Projectspring.Home.VO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService implements HomeServicein{

    private final HomeDAO homeDAO;

    @Override
    public List<GetByGroupIdVO> getByGroupId(HomeApiController.FindNeedVO findNeedVO) throws Exception {
        return homeDAO.getByGroupId(findNeedVO);
    }

    @Override
    public String getUserMood(int user_id) throws Exception {
        return homeDAO.getUserMood(user_id);
    }

    @Override
    public List<FamilyListNeedVO> getFamilyList(int group_id) throws Exception {
        return homeDAO.getFamilyList(group_id);
    }

    @Override
    public String getUserName(int user_id) throws Exception {
        return homeDAO.getUserName(user_id);
    }

    @Override
    public List<TodayAnniversaryVO> getCalendar(HomeApiController.FindNeedVO findNeedVO) throws Exception {
        return homeDAO.getCalendar(findNeedVO);
    }

    @Override
    public List<ImagePathVO> getUserImagePath(int user_id) throws Exception {
        return homeDAO.getUserImagePath(user_id);
    }

    @Override
    public void putImagePath(HomeApiController.putImageVO putImageVO) throws Exception {
        homeDAO.putImagePath(putImageVO);
    }

    @Override
    public String CheckCalendar(String calendar_date) throws Exception {
        return homeDAO.CheckCalendar(calendar_date);
    }

    @Override
    public float GroupBonding(int group_id) throws Exception {
        return homeDAO.GroupBonding(group_id);
    }
}
