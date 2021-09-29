package Project.Projectspring.Group.DAO;


import Project.Projectspring.Group.VO.GroupVO;

public interface GroupDAOin {

    void createGroup(GroupVO groupVO) throws Exception;

    void createCode(GroupVO groupVO) throws Exception;
}
