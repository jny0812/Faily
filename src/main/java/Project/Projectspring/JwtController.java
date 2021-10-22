package Project.Projectspring;

import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Join.Service.JoinService;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Join.VO.JwtTokenVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
public class JwtController {

    private final JoinController joinController;
    private final HttpServletRequest request;
    private final JoinService joinService;

    /** 새로운 토큰 생성 **/
    @RequestMapping(value = "/JwtToken",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> JwtToken() throws Exception {

        String e_mail = joinController.getSubject();

        String token = joinController.makeJwtToken(e_mail);
        HashMap<String, Object> result = new HashMap<>();

        try{

            JwtTokenVO jwtTokenVO = new JwtTokenVO(token,e_mail);
            joinService.updateJwtToken(jwtTokenVO);

            result.put("isSuccess", true);
            result.put("code", 200);
            result.put("message", "토큰 재발급 성공");
            result.put("jwt_token", token);
        }
        catch (Exception e) {

            result.put("isSuccess", false);
            result.put("code", 301);
            result.put("message", "유효하지 않은 사용자입니다.");

        }

        return result;
    }
}
