package Project.Projectspring.Group.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GroupUserVO {
    int group_id;
    String group_code;
    String user_email;

}
