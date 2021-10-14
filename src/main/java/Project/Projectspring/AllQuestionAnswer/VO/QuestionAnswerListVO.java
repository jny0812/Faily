package Project.Projectspring.AllQuestionAnswer.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class QuestionAnswerListVO {

    private List<AllAnswerVO> allAnswerVOS;
//    private String question_time;
//    private String question;
//    private int question_id;
    private List<QuestionListVO> questionListVOS;
}
