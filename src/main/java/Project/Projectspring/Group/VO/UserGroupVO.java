package Project.Projectspring.Group.VO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserGroupVO {

    int group_id;
    String group_code;
    String user_email;
    int user_group_id;

}