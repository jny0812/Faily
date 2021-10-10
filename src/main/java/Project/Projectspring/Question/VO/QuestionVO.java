package Project.Projectspring.Question.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionVO {

    private String question;
    private int answer_status;
    private String question_time;

    public QuestionVO(String question, String question_time) {
        this.question = question;
        this.answer_status = answer_status;
        this.question_time = question_time;
    }
}
