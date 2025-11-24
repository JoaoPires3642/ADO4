package suaescola.suaescola.config;

import suaescola.suaescola.model.Usuario;
import suaescola.suaescola.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRoles(Set.of("ROLE_ADMIN", "ROLE_USER"));
                admin.setEnabled(true);
                usuarioRepository.save(admin);
                System.out.println("Usuário admin criado: admin / admin123");
            } else {
                System.out.println("Usuário admin já existe");
            }
        };
    }
}
