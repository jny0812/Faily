package Project.Projectspring.Join.Controller;

import Project.Projectspring.Join.Service.JoinService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Random;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MailSendController {

    private final JoinService joinService;

    private final JavaMailSender mailSender;


    /** 이메일 보내기 **/
    @RequestMapping(value = "/SendEmail", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> mailSending(@Valid @RequestBody HashMap<String, String > e_mail) {

        HashMap<String, Object> result = new HashMap<>();

        if (emailCheck(e_mail.get("user_email")) == 1) {

            result.put("isSuccess", false);
            result.put("code", 302);
            result.put("message", "잘못된 이메일 형식입니다.");

            return result;
        }

        log.warn(String.valueOf(e_mail));

        Random rand = new Random();

        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        String setfrom = "wkdskdus@gamil.com";
        String title = "테스트 인증번호."; // 제목
        String content =

                System.getProperty("line.separator") + //한줄씩 줄간격을 두기위해 작성


                        System.getProperty("line.separator") +

                        System.getProperty("line.separator") +

                        " 인증번호는 " + numStr + " 입니다. ";

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

            result.put("isSuccess", true);
            result.put("VerificationCode", numStr);
            result.put("code", 200);
            result.put("message", "인증번호가 성공적으로 발급되었습니다.");

            return result;
        }
        catch (Exception e) {
            e.printStackTrace();

            result.put("isSuccess", false);
            result.put("code", 301);
            result.put("message", "유효하지 않은 사용자입니다.");

            return result;
        }}


    public int emailCheck(String e_mail) {
        if (e_mail.contains("@")) {
            if (e_mail.split("@")[1].contains(".")) {
                return 0;
            }
        } return 1;
    }}


