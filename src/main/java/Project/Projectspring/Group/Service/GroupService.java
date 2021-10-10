package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.DAO.GroupDAO;
import Project.Projectspring.Group.VO.GroupCreateTimeVO;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService implements GroupServicein {

    private final GroupDAO groupDAO;


    @Override
    public void createGroup(GroupCreateTimeVO groupCreateTimeVO) throws Exception {
        groupDAO.createGroup(groupCreateTimeVO);
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

    @Override
    public Integer isExisted(String e_mail) throws Exception {
        return groupDAO.isExisted(e_mail);
    }

    @Override
    public String groupCode(int group_id) throws Exception {
        return groupDAO.groupCode(group_id);
    }


}
