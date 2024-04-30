package bird.JavaBird.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class JwtTokenizer {

    private final byte[] secretKey;

    public final static Long TOKEN_EXPIRE_COUNT = 1 * 24 * 60 * 60 * 1000L;// 1 days

    public JwtTokenizer(@Value("${jwt.secretKey}") String secret) {
        this.secretKey = secret.getBytes(StandardCharsets.UTF_8);
    }

    private String createToken(Long id, String email, String name, List<String> roles, byte[] secretKey) {
        Claims claims = Jwts.claims().setSubject(email);

        claims.put("roles", roles);
        claims.put("memberId", id);
        claims.put("name", name);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + TOKEN_EXPIRE_COUNT))
                .signWith(getSigningKey(secretKey))
                .compact();
    }

    public Long getMemberIdFromToken(String token) {
        String[] tokenArr = token.split(" ");
        token = tokenArr[1];
        Claims claims = parseToken(token);
        return Long.valueOf((Integer)claims.get("memberId"));
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(secretKey))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static Key getSigningKey(byte[] secretKey) {
        return Keys.hmacShaKeyFor(secretKey);
    }
}
