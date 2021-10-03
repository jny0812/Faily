package Project.Projectspring.Join.Controller;

public class emailcheck{

    public int tesll(String email) {
        if (check(email) == 1) {
            System.out.println("실패");
            return 1;
        }
        else{

            System.out.println("성공");
            return 0;
        }
    }

    public int check(String email) {
        if (email.contains("@")) {
            if (email.split("@")[1].contains(".")) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        emailcheck testclass = new emailcheck();
        testclass.tesll("wokdjfl@sdsdfsd");
    }

}
