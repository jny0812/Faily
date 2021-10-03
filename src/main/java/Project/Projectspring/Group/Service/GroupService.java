package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.DAO.GroupDAO;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import Project.Projectspring.Join.VO.JoinVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService implements GroupServicein {

    private final GroupDAO groupDAO;


    @Override
    public void createGroup(String group_code) throws Exception {
        groupDAO.createGroup(group_code);
    }

    @Override
    public void createCode(GroupVO groupVO) throws Exception {
        groupDAO.createCode(groupVO);
    }

    @Override
    public String codeCheck(GroupVO groupVO) throws Exception {

        return groupDAO.codeCheck(groupVO);
    }

    //user 테이블에 group_id update (코드 발급 시)
    @Override
    public int groupIdCheck(String group_code) throws Exception {
        return groupDAO.groupIdCheck(group_code);
    }

    @Override
    public void updateUserGroupId(UserGroupVO userGroupVO) throws Exception {
        groupDAO.updateUserGroupId(userGroupVO);
    }


}
