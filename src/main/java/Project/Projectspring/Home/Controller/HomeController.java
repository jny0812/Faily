//package Project.Projectspring.Home.Controller;
//
//import Project.Projectspring.Join.Controller.JoinController;
//import Project.Projectspring.Question.Service.QuestionService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.hibernate.mapping.Join;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//public class HomeController {
//
//    private final JoinController joinController;
//    private final QuestionService questionService;
//
//    /** 홈 화면 **/
//    @RequestMapping(value = "/Home", method = RequestMethod.GET)
//    @ResponseBody
//    public Object Home() throws Exception {
//
//        String a = joinController.remakeJwtToken();
//        String e_mail = joinController.getSubject(); //이메일 추출
//        int user_id = questionService.userIdCheck(e_mail);
//        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출
//
//
//    }
//
//}
