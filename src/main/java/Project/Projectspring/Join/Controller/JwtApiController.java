package Project.Projectspring.Join.Controller;

import Project.Projectspring.Join.Service.SecurityService;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtApiController {

    private final JwtController jwtController;


    private final HttpServletResponse response;
    private final HttpServletRequest request;
//
//    @After("execution(* Project.Projectspring.Join.Controller.JoinController..*(..))")
//    public void createRefreshJwtToken() {
//        String Token = jwtController.makeJwtToken();
//        String BarerToken = "Barer " + Token;
//        response.setHeader("Authorization",BarerToken);
//    }

//    @Before("execution(* Project.Projectspring.Join.Controller.JoinController..*(..))")
//    public void checkValidToken() throws JsonParseException {
//
//        String[] token = request.getHeader("token").split(" ");
//
//        Map<String, Object> map = jwtController.decryptValidJwtToken(token[1]);
//        log.info("\nmap : " + map.toString());
//    }

//    public static void main(String[] args) throws JsonParseException{
//        JwtController jwtController = new JwtController();
//        String token = jwtController.makeJwtToken();
//        System.out.println("token =" + token);
//        System.out.println("decrypt : " + jwtController.decryptValidJwtToken(token).toString());
//
//        System.out.println("\n\ndate :" + new Date().getTime());
//    }


}

