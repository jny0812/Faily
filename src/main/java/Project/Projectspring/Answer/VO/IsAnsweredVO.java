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

    public IsAnsweredVO() {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_group_id = user_group_id;
        this.status = status;
    }
}
