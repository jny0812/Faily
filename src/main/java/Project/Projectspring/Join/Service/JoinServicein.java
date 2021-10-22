package Project.Projectspring.Join.Service;


import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Join.VO.JwtTokenVO;


//@Headers("Content-Type: application/x-www-form-urlencoded")
public interface JoinServicein {

        void create(JoinVO joinVO) throws Exception;

        String loginCheck(JoinVO joinVO) throws Exception;

        String passwordCheck(JoinVO joinVO) throws Exception;

        void updateJwtToken(JwtTokenVO jwtTokenVO) throws Exception;
}