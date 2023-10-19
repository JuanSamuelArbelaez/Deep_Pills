package deep_pills.utils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
@Component
public class JWTUtils {
    @Value("${jwt.secret}")
    private String claveSecreta;
    public String generarToken(String email, Map<String, Object> claims){
        Instant now = Instant.now();
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5L, ChronoUnit.MINUTES)))
                .signWith( getKey() )
                .compact();

    }
    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException,
            UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        return Jwts.parserBuilder()
                .setSigningKey( getKey() )
                .build()
                .parseClaimsJws(jwtString);

    }
    private Key getKey(){
        return new SecretKeySpec(Base64.getDecoder().decode(claveSecreta),
                SignatureAlgorithm.HS256.getJcaName());
    }
}
