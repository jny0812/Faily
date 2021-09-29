package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.VO.GroupVO;
import Project.Projectspring.Join.VO.JoinVO;

public interface GroupServicein {

    void createGroup(GroupVO groupVO) throws Exception;

    void createCode(GroupVO groupVO) throws Exception;

    String codeCheck(GroupVO groupVO) throws Exception;
}
