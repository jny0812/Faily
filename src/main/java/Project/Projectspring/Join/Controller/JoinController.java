package Project.Projectspring.Join.Controller;


import Project.Projectspring.Join.Service.JoinService;
import Project.Projectspring.Join.VO.JoinVO;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

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

        if(joinService.loginCheck(joinVO) == null){

            result.put("isSuccess", false);
            result.put("code",404);
            result.put("message","존재하지 않는 회원정보입니다.");

            return result;
        }
        else{
            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","로그인에 성공하였습니다.");
            return result;
        }
    }


    //이메일보내기
    @RequestMapping( value = "/SendEmail" , method=RequestMethod.POST )
    @ResponseBody
    public HashMap<String, Object> mailSending (@RequestBody HashMap<String, String > e_mail){

        log.warn(String.valueOf(e_mail));

        Random rand  = new Random();

        HashMap<String, Object> result = new HashMap<>();

        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        String setfrom = "wkdskdus@gamil.com";
        String title = "테스트 인증번호."; // 제목
        String content =

                System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성



                        System.getProperty("line.separator")+

                        System.getProperty("line.separator")+

                        " 인증번호는 " +numStr+ " 입니다. ";


        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");

            messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(e_mail.get("user_email")); // 받는사람 이메일
            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용

            mailSender.send(message);

            System.out.println(e_mail.get("user_email"));

            result.put("isSuccess",true);
            result.put("code",200);
            result.put("message","인증번호가 성공적으로 발급되었습니다.");
            result.put("인증번호",numStr);

        } catch (Exception e) {
            System.out.println(e);

            result.put("isSuccess",false);
            result.put("code",404);
            result.put("message","네트워크 오류");
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