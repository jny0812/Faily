package Project.Projectspring.Join.VO;


//import javax.validation.constraints.Email;
//import javax.validation.constraints.Pattern;

public class JoinVO {

    //@Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+")
    private String user_email;
    private String user_pw;
    private String user_name;
    private String user_bdate;


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
        this.user_pw=  user_pw;
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

    public JoinVO(String user_email, String user_pw, String user_name, String user_bdate) {
        this.user_email = user_email;
        this.user_pw = user_pw;
        this.user_name = user_name;
        this.user_bdate = user_bdate;
    }

    @Override
    public String toString() {
        return "JoinVO{" +
                "user_email='" + user_email + '\'' +
                ", user_pw='" + user_pw + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_bdate='" + user_bdate + '\'' +
                '}';
    }
}