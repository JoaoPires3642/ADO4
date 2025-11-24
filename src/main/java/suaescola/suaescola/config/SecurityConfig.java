package suaescola.suaescola.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import suaescola.suaescola.service.UsuarioService;

@Configuration
public class SecurityConfig {

    private final UsuarioService usuarioService;

    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication provider using the custom UserDetailsService
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usuarioService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/css/**", "/js/**", "/h2-console/**", "/", "/cursos", "/api/cursos/**").permitAll()
            .requestMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
          )
          .formLogin(form -> form
            .loginPage("/login")
            .permitAll()
          )
          .logout(logout -> logout.permitAll())
          // allow frames for H2 console
          .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        // disable csrf for h2-console (dev only)
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        // register our authentication provider
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
