package Project.Projectspring.Question.Controller;

import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.Question.VO.GroupQuestionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
@Slf4j
@RequiredArgsConstructor
@Component
public class QuestionController {

    private final QuestionService questionService;
    private final JoinController joinController;


    //오늘의 질문 불러오기
    @RequestMapping(value = "/todayQuestion", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> todayQuestion() throws Exception {

        HashMap<String, Object> result = new HashMap<>();
            try{
                String a = joinController.remakeJwtToken();
                String e_mail = joinController.getSubject(); //이메일 추출
                int user_id = questionService.userIdCheck(e_mail);
                int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출

                if(questionService.groupQuestionId(group_id)==1){
                    String question = questionService.bringQuestion(group_id);

                    result.put("isSuccess", true);
                    result.put("code",200);
                    result.put("message","오늘의 질문을 불러왔어요!");
                    result.put("todayQuestion", question);
                    questionService.ChangeGroupQuestion(group_id);  //group_question_id + 1

                }
                else{
                    questionService.statusChangeToZero(user_id); //답변 미완료 상태로 변경

                    Date time = new Date();
                    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(time);
                    //cal.add(Calendar.HOUR, 9);
                    String question_time = sdformat.format(cal.getTime());  //'오늘의 질문' 날짜

                    String question = questionService.bringQuestion(group_id);  //question 불러오기
                    log.warn(question);
                    int question_id = questionService.questionId(question);

                    GroupQuestionVO groupQuestionVO = new GroupQuestionVO(group_id,question_id,question_time);

                    int CheckExist = questionService.CheckQuestionInfo(groupQuestionVO);
                    if(CheckExist==0) {
                    questionService.createGroupQuestion(groupQuestionVO);  }  //테이블에 insert

                    result.put("isSuccess", true);
                    result.put("code",200);
                    result.put("message","오늘의 질문을 불러왔어요!");
                    result.put("todayQuestion", question);

               } return result;

            } catch (Exception e) {
                log.warn(String.valueOf(e));
                result.put("isSuccess", false);
                result.put("code", 301);
                result.put("message", "유효하지 않은 사용자입니다.");

                return result;
            }
    }

    @Scheduled(cron = "0 * * * * *",zone = "Asia/Seoul")
    public void Scheduling() throws Exception {

        Date time = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        Date d1 = f.parse("00:00");
        time = f.parse(sdformat.format(time));
        int timecheck = d1.compareTo(time);
        log.warn(String.valueOf(timecheck));

        if(  timecheck == 0) {
            questionService.updateGroupQuestionId();
        }
    }


    }