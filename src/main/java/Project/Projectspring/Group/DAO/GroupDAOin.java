package Project.Projectspring.Group.DAO;


import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Join.VO.JoinVO;

public interface GroupDAOin {

    void createGroup(GroupVO groupVO) throws Exception;

    void createCode(GroupVO groupVO) throws Exception;

    String codeCheck(GroupVO groupVO) throws Exception;
}
