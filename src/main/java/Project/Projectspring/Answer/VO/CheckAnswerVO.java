package Project.Projectspring.Answer.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CheckAnswerVO {

    private int question_index;
    private int user_id;
}
