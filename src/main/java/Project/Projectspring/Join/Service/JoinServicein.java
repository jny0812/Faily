package Project.Projectspring.Join.Service;


import Project.Projectspring.Join.VO.JoinVO;

public interface JoinServicein {

        void create(JoinVO joinVO) throws Exception;

        String loginCheck(JoinVO joinVO) throws Exception;


}