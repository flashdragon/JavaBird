package bird.JavaBird.config;

import bird.JavaBird.security.EntryPointUnauthorizedHandler;
import bird.JavaBird.security.JwtAuthenticationFilter;
import bird.JavaBird.security.JwtAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    public SecurityConfig(EntryPointUnauthorizedHandler entryPointUnauthorizedHandler,
                          JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        httpSecurity
                .addFilterBefore(
                        new JwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider)
                .formLogin((formLogin)-> formLogin.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling((except)->except.authenticationEntryPoint(entryPointUnauthorizedHandler));

        return httpSecurity.build();
    }
}
