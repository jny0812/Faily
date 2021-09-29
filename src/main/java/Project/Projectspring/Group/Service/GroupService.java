package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.DAO.GroupDAO;
import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Join.VO.JoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService implements GroupServicein {

    private final GroupDAO groupDAO;

    @Autowired
    public GroupService(GroupDAO groupDAO) {this.groupDAO = groupDAO;}


    @Override
    public void createGroup(GroupVO groupVO) throws Exception {
        groupDAO.createGroup(groupVO);
    }

    @Override
    public void createCode(GroupVO groupVO) throws Exception {
        groupDAO.createCode(groupVO);
    }

    @Override
    public String codeCheck(GroupVO groupVO) throws Exception {

        return groupDAO.codeCheck(groupVO);
    }
}
