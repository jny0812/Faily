package Project.Projectspring.Question.VO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AllQuestionsVO {

    private String question;
    private String user_name;
    private String answer;
    private String answer_time;
    private String question_time;
    private int answer_group_id;
    private int answer_question_id;


}
