package Project.Projectspring.Home.VO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamilyListNeedVO {

    private String user_name;
    private float user_bonding;
    private String user_image;
    private String user_mood;
}
