package Project.Projectspring.Question.Controller;

import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.Question.VO.AllQuestionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AllQuestionController {

    private final QuestionService questionService;
    private final JoinController joinController;

    //전체 질문 불러오기
    @RequestMapping(value = "/allQuestion", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> allQuestion() throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail); //user_id 추출

        if(e_mail == null) {

            result.put("isSuccess", false);
            result.put("code",404);
            result.put("message","유효하지 않은 사용자입니다.");
        }
        else {
            int group_id = questionService.questionUserGroupId(user_id); //user의 group_id

            List<AllQuestionVO> questions = questionService.allQuestion(group_id); //question 불러오기

            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","전체 질문을 불러왔습니다.");
            result.put("questions",questions);
        }
        return result;
 }}
