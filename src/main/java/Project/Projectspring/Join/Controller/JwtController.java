package Project.Projectspring.Join.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Map;

@Slf4j
@Controller
public class JwtController {

    private final String SECRET_KEY = "aasjjkjaskjdl1k2naskjkdakj34c8sa";
    private final Long EXPIRATION_TIME = 15 * 60 * 1000L;

    public String makeJwtToken() {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh_token") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + EXPIRATION_TIME)) // (4)
                .claim("id","user_name") // (5)
                .claim("email", "wkdskdus@gmail.com")
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY) // (6)
                .compact();
    }

    public Map<String, Object> decryptValidJwtToken(String token) throws JsonParseException {
        Map<String, Object> map = null;

        map = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        log.info(map.toString());
        return map;
    }

    public static void main(String[] args) throws JsonParseException{
        JwtController jwtController = new JwtController();
        String token = jwtController.makeJwtToken();
        System.out.println("token = " + token);
    }
}
