package Project.Projectspring.Group.DAO;

import Project.Projectspring.Group.VO.GroupVO;

public interface GroupDAOin {

    void createGroup(String group_code) throws Exception;

    void createCode(GroupVO groupVO) throws Exception;

    String codeCheck(GroupVO groupVO) throws Exception;
}
