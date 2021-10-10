package Project.Projectspring.Question.Controller;

import Project.Projectspring.Answer.Service.IsAnsweredService;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.Question.VO.AllQuestionVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    private final IsAnsweredService isAnsweredService;

    //전체 질문 불러오기
//    @RequestMapping(value = "/allQuestion", method = RequestMethod.GET)
//    @ResponseBody
//    public HashMap<String, Object> allQuestion() throws Exception {
//
//        HashMap<String, Object> result = new HashMap<>();
//
//        String a = joinController.remakeJwtToken();
//        String e_mail = joinController.getSubject(); //이메일 추출
//        int user_id = questionService.userIdCheck(e_mail); //user_id 추출
//
//        if(e_mail == null) {
//
//            result.put("isSuccess", false);
//            result.put("code",404);
//            result.put("message","유효하지 않은 사용자입니다.");
//        }
//        else {
//            int group_id = questionService.questionUserGroupId(user_id); //user의 group_id
//
//            List<AllQuestionVO> questions = questionService.allQuestion(group_id); //question 불러오기
//
//            result.put("isSuccess", true);
//            result.put("code",200);
//            result.put("message","전체 질문을 불러왔습니다.");
//            //result.put("questions",questions);
//        }
//        return result;
// }

    /**전체 질문 불러오기 **/
    @RequestMapping(value = "/allQuestion", method = RequestMethod.GET)
    @ResponseBody
    public Object allQuestion() throws  Exception {

        HashMap<String, Object> result = new HashMap<>();


//        if (e_mail == null) {
//
//            result.put("isSuccess", false);
//            result.put("code",404);
//            result.put("message","유효하지 않은 사용자입니다.");
//
//            return result;
//        }
//        else {
//
//            return new AllQuestionInfo();
//        }

       return null;
    }

        @Getter
        @AllArgsConstructor
        public static class AllQuestionInfo<T> {

            String question_time;
            String question;
            boolean isAnswered;
            boolean allAnswered;
            T AnsweredInfo;
        }

        @Getter
        @AllArgsConstructor
        public static class AnswerInfo<T> {
            String user_name;
            //프로필이미지
            String answer;
            String answer_time;
        }

        public void questioninfo(List<AllQuestionInfo> list2) throws Exception {

            String a = joinController.remakeJwtToken();
            String e_mail = joinController.getSubject(); //이메일 추출
            int user_id = questionService.userIdCheck(e_mail); //user_id 추출
            int group_id = questionService.questionUserGroupId(user_id);  //질문한 user의 group_id 추출

            int user_group_id = isAnsweredService.answeredUserGroupId(e_mail);
            int answeredNumber = isAnsweredService.answeredUserNumber(user_group_id); //가족 중 답변 완료한 사람 수
            int userNumber = isAnsweredService.userNumber(user_group_id); //가족 멤버 수

            List<Map<String, Object>> quesiton_id = questionService.selectQuestions(group_id);

            for(int i = 0; i<quesiton_id.size();i++) {

                int question_id = (int) quesiton_id.get(i).get("question_id");
                String question = (String) quesiton_id.get(i).get("question");
                String question_time = (String) quesiton_id.get(i).get("question_time");

                boolean isAnswered = isAnswered(question_id);
                boolean allAnswered = allanswered(userNumber,answeredNumber);

//                AnswerInfo<String> Answeredinfo = answerinfo();

//                List<AnswerInfo> Answeredinfo = answerinfo(<AnswerInfo>))

//                AllQuestionInfo allQuestionInfo = new AllQuestionInfo(question, question_time,isAnswered, allAnswered,Answeredinfo);

            }
     }

        public void answerinfo(List<AnswerInfo> list) throws Exception{

                for(int i=0; i< list.size();i++) {

//                    List<AllQuestionVO> UserAnswer = questionService.UserAnswer(answer_group_id);
//                    String user_name = UserAnswer.get(i).getUser_name();
//                    String answer = UserAnswer.get(i).getAnswer();
//                    String answer_time = UserAnswer.get(i).getAnswer_time();
//
//                    AnswerInfo answerInfo = new AnswerInfo(user_name, answer, answer_time);
//                    list.add(answerInfo);
                }

    }

    public boolean isAnswered(int question_id) throws Exception {
        if (isAnsweredService.isAnsweredUser(question_id) == null) {
           return true;
        } else {
            return false;
        }
    }

    public boolean allanswered(int userNumber,int answeredNumber) {
        if(userNumber == answeredNumber) {
            return true;
        } else { return false;} }
}
