package Project.Projectspring.Join.Controller;


import Project.Projectspring.Join.Service.JoinService;
import Project.Projectspring.Join.Service.SecurityService;
import Project.Projectspring.Join.VO.JoinVO;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }


    @RequestMapping(value = "/joindo",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> JoinDo(@RequestBody JoinVO joinVO) throws SQLException {
        HashMap<String,Object> result = new HashMap<>();

        try {
            joinService.create(joinVO);

            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","회원가입 성공!");
        }
        catch(Exception e) {

            result.put("isSuccess", false);
            result.put("code",409);
            result.put("message","이미 존재하는 이메일입니다.");

        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public HashMap<String, Object> Login(@RequestBody JoinVO joinVO) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        JoinController joinController = new JoinController(joinService);
        String token = joinController.makeJwtToken();

        if(joinService.loginCheck(joinVO) == null){

            result.put("isSuccess", false);
            result.put("code",404);
            result.put("message","존재하지 않는 회원정보입니다.");

        }
        else{
            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","로그인에 성공하였습니다.");
            result.put("token", token);

        }
        return result;
    }

    public String makeJwtToken() {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("id", "아이디") // (5)
                .claim("email", "wkdskdus@gmail.com")
                .signWith(SignatureAlgorithm.HS256, "secret") // (6)
                .compact();
    }

}