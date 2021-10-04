package Project.Projectspring.Answer.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IsAnsweredVO {
    private int user_id;
    private String user_email;
    private int user_group_id;
    private int status;
}
