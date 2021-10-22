package Project.Projectspring.Answer.VO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class AnswerVO {

    private int answer_id;
    private String answer;
    private int user_id;
    private String answer_time;
    private int question_index;
    private int answer_status;
    private int group_id;


}
