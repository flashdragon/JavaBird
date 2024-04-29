package bird.JavaBird.config;

import bird.JavaBird.security.EntryPointUnauthorizedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    public SecurityConfig(EntryPointUnauthorizedHandler entryPointUnauthorizedHandler) {
        this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin((formLogin)-> formLogin.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling((except)->except.authenticationEntryPoint(entryPointUnauthorizedHandler));

        return httpSecurity.build();
    }
}
