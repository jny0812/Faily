package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Join.VO.JoinVO;

public interface GroupServicein {

    void createGroup(String group_code) throws Exception;

    void createCode(GroupVO groupVO) throws Exception;

    String codeCheck(GroupVO groupVO) throws Exception;

    //user 테이블에 group_id update
    int groupIdCheck(String group_code) throws Exception;

    //void updateUserGroupId(int group_id, String user_email) throws Exception;
}
