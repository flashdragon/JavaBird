package bird.JavaBird.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    static String _401 = "{\"success\":false,\"response\":null,\"error\":{\"message\":\"Unauthorized\",\"status\":401}}";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("content-type", "application/json");
        response.getWriter().write(_401);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
