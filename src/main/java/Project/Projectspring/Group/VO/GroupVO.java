package Project.Projectspring.Group.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class GroupVO {

    private int group_id;
    private String group_code;

}
