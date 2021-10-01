package Project.Projectspring.Join.Service;


import Project.Projectspring.Join.VO.JoinVO;
import org.springframework.messaging.handler.annotation.Headers;

//@Headers("Content-Type: application/x-www-form-urlencoded")
public interface JoinServicein {

        void create(JoinVO joinVO) throws Exception;

        String loginCheck(JoinVO joinVO) throws Exception;
}