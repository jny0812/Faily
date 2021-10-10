package Project.Projectspring.Answer.VO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AnswerUpdateVO {

    private int user_id;
    private String answer_time;
}
