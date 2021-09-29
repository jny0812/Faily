package Project.Projectspring.Group.VO;

import javax.persistence.criteria.CriteriaBuilder;

public class GroupVO {

    private int group_id;
    private String group_name;
    private String group_code;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() { return group_name;}

    public void setGroup_name(String group_name) {this.group_name = group_name;}

    public String getGroup_code() { return  group_code;}

    public void setGroup_code(String group_code) { this.group_code = group_code;}



    public GroupVO(int group_id, String group_name, String group_code, int group_user_id) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_code = group_code;
    }

    @Override
    public String toString() {
        return "GroupVO{" +
                "group_id='" + group_id + '\'' +
                ", group_name='" + group_name + '\'' +
                ", group_code='" + group_code + '\'' +
                '}';
    }

}
