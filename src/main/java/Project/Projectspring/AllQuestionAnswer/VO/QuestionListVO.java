package Project.Projectspring.AllQuestionAnswer.VO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class QuestionListVO {

    private Date question_time;
    private String question;
    private int question_id;
//    private List<AllAnswerVO> allAnswerVOS;
}
