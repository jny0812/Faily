package Project.Projectspring.Join.Service;


import Project.Projectspring.Join.VO.JoinVO;

public interface JoinServicein {

        Integer create(JoinVO joinVO) throws Exception;

        String loginCheck(JoinVO joinVO) throws Exception;

        //String checkemail(String user_email) throws Exception;

}