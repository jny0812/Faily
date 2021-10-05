package Project.Projectspring.Question.VO;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AllQuestionVO {

    private int group_id;
    private int question_id;
    private String question;

    public AllQuestionVO( int group_id, int question_id, String question) {
        this.group_id = group_id;
        this.question_id = question_id;
        this.question = question;
    }
}
