package Project.Projectspring.Calender.Controller;

import Project.Projectspring.AllQuestionAnswer.Controller.AllQuestionAnswerController;
import Project.Projectspring.AllQuestionAnswer.VO.AllAnswerVO;
import Project.Projectspring.Calender.Service.CalenderService;
import Project.Projectspring.Calender.VO.AllCalendarVO;
import Project.Projectspring.Calender.VO.CalendarListVO;
import Project.Projectspring.Calender.VO.CalenderVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CalenderController {

    private final JoinController joinController;
    private final CalenderService calenderService;
    private final QuestionService questionService;
    private Object List;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CalendarList{
        String calendar_date;
        List<AllCalendarVO> allCalendarVOS;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer{
        boolean IsSuccess;
        int code;
        String message;
        Map<String,Object> calendar;
    }

    /** 전체 달력 불러오기 **/
    @RequestMapping(value = "/Calendar", method = RequestMethod.GET)
    @ResponseBody
    public Object Calender() throws Exception {

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

            responseAnswer response = new responseAnswer(true, 200, "전체 일정을 불러왔습니다.", null);

            Map<String, Object> map = new HashMap<>();

            List<CalenderVO> calender = calenderService.Calender(group_id);

            for(int i=0; i<calender.size(); i++){
                String calendar_date = calender.get(i).getCalendar_date();
                String calendar_category = calender.get(i).getCalendar_category();
                String calendar_name = calender.get(i).getCalendar_name();
                String calendar_place = calender.get(i).getCalendar_place();
                String calendar_memo = calender.get(i).getCalendar_memo();
                String calendar_start_time = calender.get(i).getCalendar_start_time();
                String calendar_end_time = calender.get(i).getCalendar_end_time();

                CalendarList calendarList = new CalendarList(calendar_date,null);

                CalendarListVO calendarListVO = new CalendarListVO(group_id,calendar_date);
                List<AllCalendarVO> allCalendarVOS = calenderService.CalendarList(calendarListVO);

                calendarList.setAllCalendarVOS(allCalendarVOS);
                map.put(calendar_date,calendarList);

            }
            response.setCalendar(map);
            return response;
            }
    }


    /** 달력 추가하기 **/
    @RequestMapping(value = "/addCalendar", method = RequestMethod.POST)
    @ResponseBody
    public Object addCalendar( @RequestBody CalenderVO calenderVO) throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출

        if (e_mail == null) {

            result.put("isSuccess", false);
            result.put("code", 302);
            result.put("message", "유효하지 않은 사용자입니다.");

        } else {

            calenderVO.setGroup_id(group_id);
            calenderVO.setUser_id(user_id);

            calenderService.addCalendar(calenderVO);

            HashMap<String,Object> calendar = new HashMap<>();

            String calendar_date = calenderVO.getCalendar_date();

            calendar.put("calendar_category",calenderVO.getCalendar_category());
            calendar.put("calendar_name",calenderVO.getCalendar_name());
            calendar.put("calendar_place",calenderVO.getCalendar_place());
            calendar.put("calendar_memo",calenderVO.getCalendar_memo());
            calendar.put("calendar_start_time",calenderVO.getCalendar_start_time());
            calendar.put("calendar_end_time",calenderVO.getCalendar_end_time());

            result.put("isSuccess", true);
            result.put("code", 200);
            result.put("message", "일정이 추가되었습니다.");
            result.put(calendar_date,calendar);

        }
        return result;
    }
    }
