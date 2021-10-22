package Project.Projectspring.Join.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class JwtTokenVO {

    private String token;
    private String e_mail;
}
