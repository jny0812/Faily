package Project.Projectspring.Join.Service;


import Project.Projectspring.Join.DAO.JoinDAO;
import Project.Projectspring.Join.VO.JoinVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService implements JoinServicein{

    private final JoinDAO joinDAO;

    @Override
    public void create(JoinVO joinVO) throws Exception {
         joinDAO.create(joinVO);
    }

    @Override
    public String loginCheck(JoinVO joinVO) throws Exception {

        return joinDAO.loginCheck(joinVO);
    }

}