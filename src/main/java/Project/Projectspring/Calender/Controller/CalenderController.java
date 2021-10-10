package Project.Projectspring.Calender.Controller;

import Project.Projectspring.Calender.Service.CalenderService;
import Project.Projectspring.Calender.VO.CalenderVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CalenderController {

    private final JoinController joinController;
    private final CalenderService calenderService;
    private final QuestionService questionService;
    private Object List;

    //전체 달력 불러오기
    @RequestMapping(value = "/Calender", method = RequestMethod.GET)
    @ResponseBody
    public Object Calender() throws Exception {

       ;

        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출

        if(e_mail==null) {
            HashMap<String, Object> result = new HashMap<>();

            result.put("isSuccess", false);
            result.put("code",302);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;

        } else {

            HashMap<String, Object> result = new HashMap<>();

            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","전체 달력 일정.");

          List<CalenderVO> calender = calenderService.Calender(group_id);

             result.put("result",calender);

          return result;



            }

    }
}
