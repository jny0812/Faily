package Project.Projectspring.Join.Controller;


import Project.Projectspring.Group.Service.GroupService;
import Project.Projectspring.Join.Service.JoinService;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Question.Service.QuestionService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class JoinController {

    private final QuestionService questionService;
    private final GroupService groupService;

    private final HttpServletRequest request;
    private static final String SECRET_KEY = "aasjjkjaskjdl1k2naskjkdakj34c8sa";

    //private final JoinController joinController;
    private final JoinService joinService;
    //private final HttpServletResponse response;

    JavaMailSender mailSender;

    /** 회원가입 **/
    @RequestMapping(value = "/joindo",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> JoinDo(@Valid @RequestBody JoinVO joinVO) throws SQLException {
        HashMap<String,Object> result = new HashMap<>();
        //JoinController joinController = new JoinController(joinService);
        String token = makeJwtToken(joinVO.getUser_email());

        try {
            joinService.create(joinVO);

            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","회원가입 성공!");
            result.put("jwt_token", token);
        }
        catch(Exception e) {

            result.put("isSuccess", false);
            result.put("code",303);
            result.put("message","이미 존재하는 이메일입니다.");

        }
        return result;
    }

    /** 로그인 **/
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)

    public HashMap<String, Object> Login(@RequestBody JoinVO joinVO) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        String token = makeJwtToken(joinVO.getUser_email());
        String e_mail = joinVO.getUser_email();  // user_email 추출

        if(joinService.loginCheck(joinVO) == null){

            result.put("isSuccess", false);
            result.put("code",303);
            result.put("message","존재하지 않는 회원정보입니다.");
        }
        else if(joinService.passwordCheck(joinVO)==null) {

            result.put("isSuccess", false);
            result.put("code",302);
            result.put("message","비밀번호가 올바르지 않습니다.");
       }

        /** group에 속해있는 경우 **/
         else if(groupService.isExisted(e_mail) != null) {

            int user_id = questionService.userIdCheck(joinVO.getUser_email());
            int group_id = questionService.questionUserGroupId(user_id);
            String group_code = groupService.groupCode(group_id);

            result.put("isSuccess", true);
            result.put("code", 200);
            result.put("message", "로그인에 성공하였습니다.");
            result.put("jwt_token", token);
            result.put("group_code", group_code);
        }

        /** group이 없는 경우 **/
         else {

            int user_id = questionService.userIdCheck(joinVO.getUser_email());

                result.put("isSuccess", true);
                result.put("code",200);
                result.put("message","로그인에 성공하였습니다.");
                result.put("jwt_token", token);
            }
        return result;
        }


    //복호화
    public String getSubject() {

        String token_re = request.getHeader("jwt_token");

        Map<String,Object> map = null;
        map =  Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token_re)
                .getBody();
        return String.valueOf(map.get("email"));
    }

    //토큰 생성 함수
    public String remakeJwtToken() {

        String token_re = request.getHeader("jwt_token");

        Map<String,Object> map = null;
        map =  Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token_re)
                .getBody();
        String email = String.valueOf(map.get("email"));


        Date now = new Date();


        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("email", email) // (5)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // (6)
                .compact();
    }

    public String makeJwtToken(String email){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("email", email) // (5)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // (6)
                .compact();

    }
}