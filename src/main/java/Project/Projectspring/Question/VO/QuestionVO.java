package Project.Projectspring.Question.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionVO {

    private String question;
    private int question_number;
    private int answer_status;

    public QuestionVO(String question, int question_number) {
        this.question = question;
        this.question_number = question_number;
        this.answer_status = answer_status;
    }
}
