package Project.Projectspring.Join.VO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class JoinVO {

    private String user_email;
    private String user_pw;
    private String user_name;
    private String user_bdate;
    //private int user_group_id;

    public JoinVO(String user_email, String user_pw, String user_name, String user_bdate, int user_group_id) {
        this.user_email = user_email;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_bdate = user_bdate;
        //this.user_group_id = user_group_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_pw() {
        return user_pw;
    }

    public void setUser_pw(String user_pw) {
        this.user_pw = user_pw;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_bdate() {
        return user_bdate;
    }

    public void setUser_bdate(String user_bdate) {
        this.user_bdate = user_bdate;
    }

//    public int getUser_group_id() {
//        return user_group_id;
//    }

//    public void setUser_group_id(int user_group_id) {
//        this.user_group_id = user_group_id;
//    }





}