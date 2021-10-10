package Project.Projectspring.Answer.Controller;

import Project.Projectspring.Answer.Service.AnswerService;
import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Answer.VO.AnswerUpdateVO;
import Project.Projectspring.Answer.VO.AnswerVO;
import Project.Projectspring.Group.VO.UserGroupVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Join.VO.JoinVO;
import Project.Projectspring.Question.Controller.QuestionController;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.Question.VO.QuestionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
    private final QuestionService questionService;
    private final IsAnsweredService isAnsweredService;

    //오늘의 답변 보내기
    @RequestMapping(value = "/SendAnswer", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> createAnswer(@RequestBody AnswerVO answerVO) throws Exception {

        HashMap<String,Object> result = new HashMap<>();

        String a = joinController.remakeJwtToken();  //토큰
        String e_mail = joinController.getSubject();  //user_email 추출
        int user_id = questionService.userIdCheck(e_mail);  //user_id 추출
        int group_id = questionService.questionUserGroupId(user_id); //group_id

        if(e_mail == null) {

            result.put("isSuccess",false);
            result.put("code",400);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;}

           else if (isAnsweredService.checkUserStatus(e_mail) == 1) {

            result.put("isSuccess",false);
            result.put("code",400);
            result.put("message","이미 답변을 완료하였습니다.");

            return result;
           }

        else {
            Date time = new Date();
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance(); cal.setTime(time);
            cal.add(Calendar.HOUR, 9);
            String answer_time = sdformat.format(cal.getTime());  //답변 날짜

            int group_question_id = answerService.bringGroupQuestionId(group_id);  //group_question_id 불러오기

            answerVO.setAnswer_time(answer_time);
            answerVO.setUser_id(user_id);
            answerVO.setAnswer_question_id(group_question_id);
            answerVO.setGroup_id(group_id);

            answerService.createAnswer(answerVO); //answer 생성

            answerService.statusChangeToOne(user_id);   //답변 완료 상태로 변경

            result.put("isSuccess",true);
            result.put("code",200);
            result.put("message","답변을 완료하였습니다");

            return result;

        }
    }

}
