package bird.JavaBird.config;

import bird.JavaBird.security.EntryPointUnauthorizedHandler;
import bird.JavaBird.security.JwtAuthenticationFilter;
import bird.JavaBird.security.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final ObjectPostProcessor<Object> objectObjectPostProcessor;




    private AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider);
        return auth.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectObjectPostProcessor);
        AuthenticationManager authenticationManager = authenticationManager(builder);
        httpSecurity
                .csrf((csrf) -> csrf.disable())
                .formLogin((formLogin) -> formLogin.disable())
                .httpBasic((basic) -> basic.disable())
                .logout((logout) -> logout.disable())
                .addFilterBefore(
                        new JwtAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api", "/api/post/**", "/api/follow/**", "/api/unfollow/**").authenticated()
                        .anyRequest().permitAll()
                )
                .exceptionHandling((except)->except.authenticationEntryPoint(entryPointUnauthorizedHandler));

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return web -> web.ignoring().requestMatchers("/images/**", "/css/**");
    }
}
