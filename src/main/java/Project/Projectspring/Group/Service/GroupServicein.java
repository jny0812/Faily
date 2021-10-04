package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import Project.Projectspring.Join.VO.JoinVO;
import org.apache.ibatis.annotations.Param;

public interface GroupServicein {

    void createGroup(String group_code) throws Exception;

    String codeCheck(GroupVO groupVO) throws Exception;

    //user 테이블에 group_id update
    int groupIdCheck(String group_code) throws Exception;

    void updateUserGroupId(UserGroupVO userGroupVO) throws Exception;
}
