package Project.Projectspring.Answer.Controller;

import Project.Projectspring.Answer.Service.AnswerService;
import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Answer.VO.IsAnsweredVO;
import Project.Projectspring.Join.Controller.JoinController;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IsAnsweredController {

    private final IsAnsweredService isAnsweredService;
    private final JoinController joinController;

    IsAnsweredVO isAnsweredVO = new IsAnsweredVO();

    //답변 여부 체크하기
    @RequestMapping(value = "/isAnswered", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> createAnswer() throws Exception {

        HashMap<String,Object> result = new HashMap<>();

        String a = joinController.remakeJwtToken();  //토큰
        String e_mail = joinController.getSubject(); //email 추출

        try {



        if(isAnsweredService.checkUserStatus(e_mail)==1) { //개인 답변 완료 상태

            String user_name = isAnsweredService.userName(e_mail);

            result.put("isSuccess",true);
            result.put("code",200);
            result.put("message","답변 여부를 불러왔습니다.");
            result.put("isAnswered", true);
//            result.put("allAnswered",allanswered(userNumber,answeredNumber));
            result.put("username", user_name);


        } else {   //개인 답변 미완료 상태

            result.put("isSuccess",true);
            result.put("code",200);
            result.put("message","답변 여부를 불러왔습니다.");
            result.put("isAnswered", false);
            //result.put("답변한사람수",answeredNumber);
            //result.put("가족멤버수",userNumber);
//            result.put("allAnswered",allanswered(userNumber,answeredNumber));

        } }
        catch(Exception e) {

        result.put("isSuccess",true);
        result.put("code",404);
        result.put("message","요청한 페이지를 찾을 수 없음");
    }

        return result;
    }

    //가족 답변 여부 method
//    public boolean allanswered(int userNumber,int answeredNumber) {
//        if(userNumber == answeredNumber) {
//            return true;
//        } else { return false;} }
}