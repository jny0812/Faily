package Project.Projectspring.Join.Service;


import Project.Projectspring.Join.DAO.JoinDAO;
import Project.Projectspring.Join.VO.JoinVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService implements JoinServicein{

    private final JoinDAO joinDAO;

    @Autowired
    public JoinService(JoinDAO joinDAO) {
        this.joinDAO = joinDAO;
    }


    @Override
    public void create(JoinVO joinVO) throws Exception {
         joinDAO.create(joinVO);
    }

    @Override
    public String loginCheck(JoinVO joinVO) throws Exception {

        return joinDAO.loginCheck(joinVO);
    }

}