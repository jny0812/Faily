package Project.Projectspring.Home.DAO;

import Project.Projectspring.Home.Controller.HomeApiController;
import Project.Projectspring.Home.VO.*;

import java.util.List;

public interface HomeDAOin {

    List<GetByGroupIdVO> getByGroupId(HomeApiController.FindNeedVO findNeedVO) throws Exception;

    String getUserMood(int user_id) throws Exception;

    List<FamilyListNeedVO> getFamilyList(int group_id) throws Exception;

    String getUserName(int user_id) throws Exception;

    List<TodayAnniversaryVO> getCalendar(HomeApiController.FindNeedVO findNeedVO) throws Exception;

    List<ImagePathVO> getUserImagePath(int user_id) throws Exception;

    void putImagePath(HomeApiController.putImageVO putImageVO) throws Exception;

    Integer CheckCalendar(String calendar_date) throws Exception;

    float GroupBonding(int group_id) throws Exception;

    void putEmojibyte(HomeApiController.putEmojiVO putEmojiVO) throws Exception;
}
