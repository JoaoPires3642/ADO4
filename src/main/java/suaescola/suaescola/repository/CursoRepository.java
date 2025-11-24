package suaescola.suaescola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import suaescola.suaescola.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
