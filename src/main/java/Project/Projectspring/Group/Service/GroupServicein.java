package Project.Projectspring.Group.Service;


import Project.Projectspring.Group.VO.GroupVO;

public interface GroupServicein {

    void createGroup(GroupVO groupVO) throws Exception;

    void createCode(GroupVO groupVO) throws Exception;
}
