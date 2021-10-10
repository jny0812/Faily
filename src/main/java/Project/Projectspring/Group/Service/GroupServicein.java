package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.VO.GroupCreateTimeVO;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;

public interface GroupServicein {

    void createGroup(GroupCreateTimeVO groupCreateTimeVO) throws Exception;

    String codeCheck(GroupVO groupVO) throws Exception;

    //user 테이블에 group_id update
    int groupIdCheck(String group_code) throws Exception;

    void updateUserGroupId(UserGroupVO userGroupVO) throws Exception;

    Integer isExisted(String e_mail) throws Exception;

    String groupCode(int group_id) throws Exception;
}
