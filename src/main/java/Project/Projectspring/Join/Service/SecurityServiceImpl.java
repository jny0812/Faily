package Project.Projectspring.Join.Service;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SecurityServiceImpl {

    private static final String SECRET_KEY = "aasjjkjaskjdl1k2naskjkdakj34c8sa";

    public String createToken() {

        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("email", "user_email") // (5)
                .claim("email", "e")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // (6)
                .compact();
    }

    public Map<String, Object> getSubject(String token) {
        Map<String,Object> map = null;
        map =  Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return map;
    }

    public static void main(String[] args) {
        SecurityServiceImpl securityService = new SecurityServiceImpl();
        Map<String, Object> t = securityService.getSubject(securityService.createToken());
        System.out.println("t = " + t.toString());
    }

}
//        if (ttlMillis <= 0) {
//            throw new RuntimeException("Expiry time must be greater than Zero : ["+ttlMillis+"] ");
//        }
//        // 토큰을 서명하기 위해 사용해야할 알고리즘 선택
//        SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
//
//        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
//        return Jwts.builder()
//                .setSubject(subject)
//                .signWith(signatureAlgorithm,signingKey)
//                .setExpiration(new Date(System.currentTimeMillis()+ttlMillis))
//                .compact();
//    }