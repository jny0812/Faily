package Project.Projectspring.Join.Controller;


import Project.Projectspring.Join.Service.JoinService;
import Project.Projectspring.Join.VO.JoinVO;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@RestController
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
    public String JoinDo(JoinVO joinVO) throws Exception {

        joinService.create(joinVO);

        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public HashMap<String,String> Login(JoinVO joinVO) throws Exception {

        HashMap<String,String> result = new HashMap<>();

        if(joinService.loginCheck(joinVO) == null){

            result.put("result","false");
            result.put("message","존재하지않는 회원정보입니다.");

            return result;
        }
        else{
            result.put("result","true");
            result.put("message","로그인에 성공하였습니다.");
            result.put("fcm_token","토큰값(?/??????)");
            return result;
        }
    }

    //이메일보내기
    @RequestMapping( value = "/SendEmail" , method=RequestMethod.POST )
    @ResponseBody
    public String mailSending(String e_mail){

        Random rand  = new Random();

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
            messageHelper.setTo(e_mail); // 받는사람 이메일
            messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
            messageHelper.setText(content); // 메일 내용

            mailSender.send(message);

            System.out.println(e_mail);
        } catch (Exception e) {
            System.out.println(e);
        }

        return numStr;

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