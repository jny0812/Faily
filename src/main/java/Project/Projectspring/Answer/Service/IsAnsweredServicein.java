package Project.Projectspring.Answer.Service;

import Project.Projectspring.Answer.VO.IsAnsweredVO;

public interface IsAnsweredServicein {

    int checkUserStatus(String e_mail) throws Exception;

    int answeredUserGroupId(String e_mail) throws Exception;

    int answeredUserNumber(int user_group_id) throws Exception;

    int userNumber(int user_group_id) throws Exception;

    String userName(String e_mail) throws Exception;

}
