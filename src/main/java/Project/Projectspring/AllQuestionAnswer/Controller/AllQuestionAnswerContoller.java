package Project.Projectspring.AllQuestionAnswer.Controller;

import Project.Projectspring.AllQuestionAnswer.Service.AllQuestionAnswerService;
import Project.Projectspring.AllQuestionAnswer.VO.AllAnswerVO;
import Project.Projectspring.AllQuestionAnswer.VO.AllQuestionVO;
import Project.Projectspring.AllQuestionAnswer.VO.AnswerNeedVO;
import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Answer.VO.AnsweredgroupuserVO;
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
public class AllQuestionAnswerContoller {

    private final QuestionService questionService;
    private final JoinController joinController;
    private final IsAnsweredService isAnsweredService;
    private final AllQuestionAnswerService allQuestionAnswerService;

    /**전체 질문 불러오기 **/
    @RequestMapping(value = "/allQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Object allQuestion() throws  Exception {

        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출

        int userNumber = isAnsweredService.userNumber(group_id);   //가족 멤버 수




//        int user_group_id = isAnsweredService.answeredUserGroupId(e_mail);
//        int answeredNumber = isAnsweredService.answeredUserNumber(user_group_id); //가족 중 답변 완료한 사람 수



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
            result.put("message","전체 질문을 불러왔습니다.");

            List<AllQuestionVO> allQuestionVOS = allQuestionAnswerService.getQuestion(group_id);


            for(int i=0 ; i<allQuestionVOS.size() ; i++) {
                int question_id = allQuestionVOS.get(i).getQuestion_id();  //question_id 추출

                HashMap<String, Object> result1 = new HashMap<>();

                AnsweredgroupuserVO answeredgroupuserVO = new AnsweredgroupuserVO(question_id,group_id);
                int answeredgroupuser = isAnsweredService.answeredgroupuser(answeredgroupuserVO);



                AnswerNeedVO answerNeedVO = new AnswerNeedVO(question_id, group_id);
                List<AllAnswerVO> allAnswerVOS = allQuestionAnswerService.getAnswer(answerNeedVO);

                allQuestionVOS.add(i, allAnswerVOS);

                allQuestionVOS.get(i);

                result1.put("result",allQuestionVOS);
                result1.put("isAnswered",isAnswered(question_id));
                result1.put("allaAnswred",allanswered(userNumber,answeredgroupuser));

                return result1;
            }


            return result;

        }
    }

    public boolean isAnswered(int question_id) throws Exception {


        if (isAnsweredService.isAnsweredUser(question_id) == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean allanswered(int userNumber,int answeredgroupuser) {
        if(userNumber == answeredgroupuser) {
            return true;
        } else { return false;} }

    //답변
//    AnswerNeedVO answerNeedVO = new AnswerNeedVO(question_id, group_id);
//    List<AllAnswerVO> allAnswerVOS = allQuestionAnswerService.getAnswer(answerNeedVO);
//
//            result.put("result",calender);
}
