package bird.JavaBird.security;

import bird.JavaBird.dto.LoginInfoDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenizer jwtTokenizer;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        Claims claims = jwtTokenizer.parseToken(authenticationToken.getToken());
        Long memberId = claims.get("memberId", Long.class);
        String name = claims.get("name", String.class);
        LoginInfoDto loginInfo = new LoginInfoDto();
        loginInfo.setMemberId(memberId);
        loginInfo.setName(name);
        return new JwtAuthenticationToken(loginInfo, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
