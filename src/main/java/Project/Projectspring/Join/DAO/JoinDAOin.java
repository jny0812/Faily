package Project.Projectspring.Join.DAO;


import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Join.VO.JwtTokenVO;

public interface JoinDAOin {

    void create(JoinVO joinVO) throws Exception;

    String loginCheck(JoinVO joinVO) throws Exception;

    String passwordCheck(JoinVO joinVO) throws Exception;

    void updateJwtToken(JwtTokenVO jwtTokenVO) throws Exception;

}
