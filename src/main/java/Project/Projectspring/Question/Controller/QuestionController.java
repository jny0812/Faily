package Project.Projectspring.Question.Controller;

import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;

@RestController
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    //오늘의 질문 불러오기
    @RequestMapping(value = "/todayQuestion", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> todayQuestion() throws Exception {

        QuestionController questionController = new QuestionController(questionService);
        HashMap<String, Object> result = new HashMap<>();

        Calendar today = Calendar.getInstance();
        int date = today.get(Calendar.DATE);
        int question_number = date % 10;

        if(questionService.questionNumberCheck(question_number) == null){


        }
        return result;
    }
}
