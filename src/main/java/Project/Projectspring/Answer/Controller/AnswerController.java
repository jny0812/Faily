package Project.Projectspring.Answer.Controller;

import Project.Projectspring.Answer.Service.AnswerService;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Join.VO.JoinVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final JoinController joinController;

    //오늘의 답변 보내기
    @RequestMapping(value = "/SendAnswer", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> createAnswer(@RequestBody AnswerVO answerVO) throws Exception {

        HashMap<String,Object> result = new HashMap<>();

        try{

            //Date answer_time = new Date();
            //Calendar answer_time = Calendar.getInstance();

            answerService.createAnswer(answerVO); //answer 생성
            //answerVO.setAnswer_time((java.sql.Date) answer_time);
            answerVO.getAnswer_id(); //answer_id 추출
            //answerService.updateAnswerTime(answerVO); //answer_time 등록

            String a = joinController.remakeJwtToken();  //토큰
            String e_mail = joinController.getSubject();  //user_email 추출
            //int user_id = answerService.userIdCheck(e_mail);   //user_id 추출

            int user_id = answerVO.getAnswer_user_id();

            answerService.updateAnswerUserId(user_id);  //answer_user_id 등록

            answerService.statusChangeToOne(user_id);   //답변 완료 상태로 변경

            result.put("isSuccess",true);
            result.put("code",200);
            result.put("message","답변을 완료하였습니다");
            }

        catch (Exception e) {

            result.put("isSuccess",false);
            result.put("code",400);
            result.put("message","유효하지 않은 사용자입니다.");
        }
        return result;
    }

}
