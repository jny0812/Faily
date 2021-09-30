package Project.Projectspring.Group.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;


@Getter
@Setter
@ToString
public class GroupVO {

    private int group_id;
    private String group_code;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {this.group_id = group_id;}

    public String getGroup_code() { return  group_code;}

    public void setGroup_code(String group_code) { this.group_code = group_code;}

    public GroupVO(int group_id, String group_name, String group_code) {
        this.group_id = group_id;
        this.group_code = group_code;
    }


    @Override
    public String toString() {
        return "GroupVO{" +
                "group_id='" + group_id + '\'' +
                ", group_code='" + group_code + '\'' +
                '}';
    }


}
