package Project.Projectspring.Answer.DAO;

import Project.Projectspring.Answer.VO.AnsweredgroupuserVO;
import Project.Projectspring.Answer.VO.IsAnsweredVO;

public interface IsAnsweredDAOin {

    int checkUserStatus(String e_mail) throws Exception;

    int answeredUserGroupId(String e_mail) throws Exception;

    int answeredUserNumber(int user_group_id) throws Exception;

    int userNumber(int group_id) throws Exception;

    String userName(String e_mail) throws Exception;

    Integer isAnsweredUser(IsAnsweredVO isAnsweredVO) throws Exception;

    int answeredgroupuser(AnsweredgroupuserVO answeredgroupuserVO) throws Exception;
}
