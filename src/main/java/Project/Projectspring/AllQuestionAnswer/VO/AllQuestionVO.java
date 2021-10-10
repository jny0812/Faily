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
public class AllQuestionVO {

    private String question_time;
    private String question;
//    private boolean isAnswered;
//    private boolean allAnswered;
//    private List<AllAnswerVO> allAnswerVOS;
    private int question_id;
}
