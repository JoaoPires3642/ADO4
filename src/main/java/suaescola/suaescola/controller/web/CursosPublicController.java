package suaescola.suaescola.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import suaescola.suaescola.repository.CursoRepository;

@Controller
public class CursosPublicController {

    private final CursoRepository repo;

    public CursosPublicController(CursoRepository repo) { this.repo = repo; }

    @GetMapping("/cursos")
    public String listar(Model m) {
        m.addAttribute("cursos", repo.findAll());
        return "cursos/listar";
    }
}
