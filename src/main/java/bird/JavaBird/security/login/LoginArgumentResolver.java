package bird.JavaBird.security.login;

import bird.JavaBird.dto.LoginInfoDto;
import bird.JavaBird.security.JwtAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Login.class) != null && parameter.getParameterType() == LoginInfoDto.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication = null;
        try {
            authentication = SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception ex) {
            return null;
        }
        if (authentication == null)
            return null;
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        Object principal = authentication.getPrincipal();
        LoginInfoDto user = (LoginInfoDto) principal;
        log.info("login argumentResolver = {}", principal);
        return user;
    }
}
