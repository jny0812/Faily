package Project.Projectspring.AllQuestionAnswer.Controller;

import Project.Projectspring.AllQuestionAnswer.Service.AllQuestionAnswerService;
import Project.Projectspring.AllQuestionAnswer.VO.*;
import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Answer.VO.AnsweredgroupuserVO;
import Project.Projectspring.Answer.VO.IsAnsweredVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AllQuestionAnswerController {

    private final QuestionService questionService;
    private final JoinController joinController;
    private final IsAnsweredService isAnsweredService;
    private final AllQuestionAnswerService allQuestionAnswerService;


    @Getter
    @Setter
    @AllArgsConstructor
    public static class QuestionList{
        String question;
//        String question_time;
        List<AllAnswerVO> answerInfo;
        boolean IsAnswered;
       boolean allAnswered;

        public QuestionList(int question_id, String question, Object allAnswers, boolean isAnswered, boolean allAsnwered) {
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer{
        boolean IsSuccess;
        int code;
        String message;
        Map<String,Object> result;
    }

    /**전체 질문 불러오기 **/
    @RequestMapping(value = "/allQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Object makeAllAnswer() throws Exception {
        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출
        int userNumber = isAnsweredService.userNumber(group_id);   //가족 멤버 수

        if(a==null) {
            HashMap<String, Object> result = new HashMap<>();

            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;

        } else {
        responseAnswer response = new responseAnswer(true, 200, "전체 질문을 불러왔습니다.", null);

        Map<String, Object> map = new HashMap<>(); //(질문 불러온 날짜, 그 날짜에 대한  allanswer)


        List<QuestionListVO> questionListVOS = allQuestionAnswerService.getQuestion(group_id);

        for(int i=0; i<questionListVOS.size(); i++){
            int question_id = questionListVOS.get(i).getQuestion_id(); //question_id 추출
            String question = questionListVOS.get(i).getQuestion();
            String question_time = questionListVOS.get(i).getQuestion_time();

//            log.warn(String.valueOf(question_id));
//            log.warn(String.valueOf(question));

            IsAnsweredVO isAnsweredVO = new IsAnsweredVO(user_id,question_id);

            AnsweredgroupuserVO answeredgroupuserVO = new AnsweredgroupuserVO(question_id,group_id);
            int answeredgroupuser = isAnsweredService.answeredgroupuser(answeredgroupuserVO);

            log.warn(String.valueOf(answeredgroupuser));
            log.warn(String.valueOf(userNumber));

            boolean IsAnswered = isAnswered(isAnsweredVO);
            boolean allAnswered = allanswered(userNumber,answeredgroupuser);

//            List<Boolean> isAnswered = Collections.singletonList(isAnswered(isAnsweredVO));
//            List<Boolean> allAnswered = Collections.singletonList(allanswered(userNumber, answeredgroupuser));

            QuestionList questionList = new QuestionList(question, null, IsAnswered, allAnswered);

            AnswerNeedVO answerNeedVO = new AnswerNeedVO(question_id, group_id);
            List<AllAnswerVO> allAnswerVOS =  allQuestionAnswerService.getAnswer(answerNeedVO);

            questionList.setAnswerInfo(allAnswerVOS);
            map.put(question_time, questionList);
        }

        response.setResult(map);

        return response;} }



    public boolean isAnswered(IsAnsweredVO isAnsweredVO) throws Exception {

        if (isAnsweredService.isAnsweredUser(isAnsweredVO) == null)
            return false;
        else
            return true;
    }

    public boolean allanswered(int userNumber,int answeredgroupuser) {
        if(userNumber == answeredgroupuser) {
            return true;
        } else { return false;} }

}
