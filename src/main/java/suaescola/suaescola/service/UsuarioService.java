package suaescola.suaescola.service;

import suaescola.suaescola.model.Usuario;
import suaescola.suaescola.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository repo;
    public UsuarioService(UsuarioRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new User(u.getUsername(), u.getPassword(), u.isEnabled(),
                true, true, true,
                u.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
