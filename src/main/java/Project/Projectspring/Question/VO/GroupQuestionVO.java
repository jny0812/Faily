package Project.Projectspring.Question.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GroupQuestionVO {

    private int group_id;
    private int question_id;
    private String question_time;

}
