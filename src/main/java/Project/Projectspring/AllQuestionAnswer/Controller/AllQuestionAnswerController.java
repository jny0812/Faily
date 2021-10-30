package Project.Projectspring.AllQuestionAnswer.Controller;

import Project.Projectspring.AllQuestionAnswer.Service.AllQuestionAnswerService;
import Project.Projectspring.AllQuestionAnswer.VO.*;
import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Answer.VO.AnsweredgroupuserVO;
import Project.Projectspring.Answer.VO.IsAnsweredVO;
import Project.Projectspring.Calender.VO.AllCalendarVO;
import Project.Projectspring.FileByte;
import Project.Projectspring.Home.VO.FamilyListNeedVO;
import Project.Projectspring.Home.VO.FamilyListVO;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import javassist.bytecode.stackmap.BasicBlock;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AllQuestionAnswerController {

    private final QuestionService questionService;
    private final JoinController joinController;
    private final IsAnsweredService isAnsweredService;
    private final AllQuestionAnswerService allQuestionAnswerService;
    private final FileByte fileByte;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionList{
        String date;
        String question;
        List<AllAnswerVO> answerInfo;
        boolean IsAnswered;
        boolean allAnswered;
        int question_index;

    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer{
        boolean IsSuccess;
        int code;
        String message;
        List<QuestionList> result;
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

        try {
        if(e_mail==null) {
            HashMap<String, Object> result = new HashMap<>();

            result.put("isSuccess", false);
            result.put("code",301);
            result.put("message","유효하지 않은 사용자입니다.");

            return result;

        } else {
        responseAnswer response = new responseAnswer(true, 200, "전체 질문을 불러왔습니다.", null);

        List<QuestionListVO> questionListVOS = allQuestionAnswerService.getQuestion(group_id);
        List<QuestionList> list = new ArrayList<>();

        for(int i=0; i<questionListVOS.size(); i++){
            log.warn("vo : " + questionListVOS.get(i).toString());
            int question_id = questionListVOS.get(i).getQuestion_id(); //question_id 추출
            String question = questionListVOS.get(i).getQuestion();
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy년 MM월 dd일");
            String question_time = sdformat.format(questionListVOS.get(i).getQuestion_time());

            log.warn(String.valueOf(question_id));
            log.warn(String.valueOf(question));

            IsAnsweredVO isAnsweredVO = new IsAnsweredVO(user_id,question_id);

            AnsweredgroupuserVO answeredgroupuserVO = new AnsweredgroupuserVO(question_id,group_id);
            int answeredgroupuser = isAnsweredService.answeredgroupuser(answeredgroupuserVO);

//            log.warn(String.valueOf(answeredgroupuser));
//            log.warn(String.valueOf(userNumber));

            boolean IsAnswered = isAnswered(isAnsweredVO);
            boolean allAnswered = allanswered(userNumber,answeredgroupuser);

            QuestionList questionList = new QuestionList();
            questionList.setQuestion(question);
            questionList.setAllAnswered(allAnswered);
            questionList.setIsAnswered(IsAnswered);
            questionList.setDate(question_time);
            questionList.setQuestion_index(question_id);

            AnswerNeedVO answerNeedVO = new AnswerNeedVO(question_id, group_id);
            List<AllAnswerImageVO> allAnswerImageVOS =  allQuestionAnswerService.getAnswer(answerNeedVO);
            List<AllAnswerVO> allAnswerVOS = new ArrayList<>();

            for (int j=0;j<allAnswerImageVOS.size();j++) {
                AllAnswerVO allAnswerVO = new AllAnswerVO(allAnswerImageVOS.get(j).getUser_name(),
                        allAnswerImageVOS.get(j).getAnswer(),allAnswerImageVOS.get(j).getAnswer_date(),
                        allAnswerImageVOS.get(j).getUser_image());
//                        fileByte.transferUserFile(allAnswerImageVOS.get(j).getUser_image()));

                allAnswerVOS.add(allAnswerVO);
            }

            questionList.setAnswerInfo(allAnswerVOS);
            list.add(questionList);
        }

        response.setResult(list);

        return response;}  }
    catch (Exception e)  {
        e.printStackTrace();
        HashMap<String, Object> result = new HashMap<>();

        result.put("isSuccess", false);
        result.put("code",301);
        result.put("message","유효하지 않은 사용자입니다.");

        return result;
    }

    }

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