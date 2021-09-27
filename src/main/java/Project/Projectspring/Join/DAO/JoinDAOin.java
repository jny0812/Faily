package Project.Projectspring.Join.DAO;


import Project.Projectspring.Join.VO.JoinVO;

public interface JoinDAOin {

    int create(JoinVO joinVO) throws Exception;

    String loginCheck(JoinVO joinVO) throws Exception;
}