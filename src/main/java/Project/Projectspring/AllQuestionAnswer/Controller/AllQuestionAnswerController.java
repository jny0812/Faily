package Project.Projectspring.AllQuestionAnswer.Controller;

import Project.Projectspring.AllQuestionAnswer.Service.AllQuestionAnswerService;
import Project.Projectspring.AllQuestionAnswer.VO.*;
import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Answer.VO.AnsweredgroupuserVO;
import Project.Projectspring.Answer.VO.IsAnsweredVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import lombok.RequiredArgsConstructor;
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

    /**전체 질문 불러오기 **/
    @RequestMapping(value = "/allQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Object allQuestion( AllQuestionVO allQuestionVO) throws  Exception {

        String a = joinController.remakeJwtToken();
        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);
        int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출

        int userNumber = isAnsweredService.userNumber(group_id);   //가족 멤버 수

        HashMap<String, Object> result = new HashMap<>();

//       int user_group_id = isAnsweredService.answeredUserGroupId(e_mail);
//        int answeredNumber = isAnsweredService.answeredUserNumber(user_group_id); //가족 중 답변 완료한 사람 수

        if(e_mail==null) {

            result.put("isSuccess", false);
            result.put("code",302);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;

        } else {

            result.put("isSuccess", true);
            result.put("code",200);
            result.put("message","전체 질문을 불러왔습니다.");

            ArrayList<List<Object>> questionAnswerListVOS = new ArrayList<>();

            List<QuestionListVO> questionListVOS = allQuestionAnswerService.getQuestion(group_id);

            for(int i=0 ; i<questionListVOS.size() ; i++) {
                int question_id = questionListVOS.get(i).getQuestion_id(); //question_id 추출

//                questionAnswerListVOS.add(Collections.singletonList(questionListVOS));

                IsAnsweredVO isAnsweredVO = new IsAnsweredVO(user_id,question_id);

                AnsweredgroupuserVO answeredgroupuserVO = new AnsweredgroupuserVO(question_id,group_id);
                int answeredgroupuser = isAnsweredService.answeredgroupuser(answeredgroupuserVO);

                AnswerNeedVO answerNeedVO = new AnswerNeedVO(question_id, group_id);

                log.warn(String.valueOf(question_id));

                List<AllAnswerVO> allAnswerVOS =  allQuestionAnswerService.getAnswer(answerNeedVO);

                log.warn(String.valueOf(allAnswerVOS));

//                for(int j = 0; j<allAnswerVOS.size(); j++) {
////                    questionAnswerListVOS.get(i).get(j).setAllAnswerVOS(allAnswerVOS);
//                    questionAnswerListVOS.add(Collections.singletonList(allAnswerVOS));
//                }

               List<Boolean> isAnswered = Collections.singletonList(isAnswered(isAnsweredVO));
              List<Boolean> allAnswered = Collections.singletonList(allanswered(userNumber, answeredgroupuser));

//                QuestionListAnswerVO questionListAnswerVO = new QuestionListAnswerVO(allAnswerVOS,questionListVOS);

//                allQuestionVO.setQuestionAnswerListVOS(questionAnswerListVOS);

                allQuestionVO.setQuestionListVOS(questionListVOS);
                allQuestionVO.setAllAnswerVOS(allAnswerVOS);
                allQuestionVO.setAllAnswered(allAnswered);
                allQuestionVO.setIsAnswered(isAnswered);

                result.put("result",allQuestionVO);
//                result.put("answervo",allAnswerVOS);
            }
            return result;

        }
    }
    public boolean isAnswered(IsAnsweredVO isAnsweredVO) throws Exception {

        if (isAnsweredService.isAnsweredUser(isAnsweredVO) == null)
            return true;
        else
            return false;
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
