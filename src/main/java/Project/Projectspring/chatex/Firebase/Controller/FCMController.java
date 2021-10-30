package Project.Projectspring.chatex.Firebase.Controller;

import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.chatex.Firebase.Service.FirebaseInit;
import Project.Projectspring.chatex.Redis.Service.RedisService;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class FCMController {

    private final RedisService redisService;
    private final JoinController joinController;
    private final QuestionService questionService;

    private final FirebaseInit init;
    @GetMapping("/v1")
    public String v1(){
        init.init();
        return "firebase_fcm";
    }

    /** fcm token 저장 api */
    @PostMapping("/fcm-save")
//    public void saveFcmToken(@RequestBody TokenDTO tokenDTO) throws Exception {
    public void saveFcmToken(@RequestBody String token) throws Exception {

        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);

        redisService.setRedisStringValue(String.valueOf(user_id), token);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class TokenDTO {
        int user_id;
        String token;
    }
}
