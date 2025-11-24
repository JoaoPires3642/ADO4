package suaescola.suaescola.controller.api;

import suaescola.suaescola.model.Curso;
import suaescola.suaescola.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoApiController {
    private final CursoRepository repo;
    public CursoApiController(CursoRepository repo){ this.repo = repo; }

    @GetMapping
    public List<Curso> listar(){ return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> get(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso){
        Curso salvo = repo.save(curso);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso curso){
    return repo.findById(id)
            .map(c -> {
                c.setNome(curso.getNome());
                c.setDescricao(curso.getDescricao());
                c.setDuracaoHoras(curso.getDuracaoHoras());
                return ResponseEntity.ok(repo.save(c));
            })
            .orElse(ResponseEntity.notFound().build());
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
