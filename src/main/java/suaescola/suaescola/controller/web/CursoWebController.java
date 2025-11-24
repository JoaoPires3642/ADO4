package suaescola.suaescola.controller.web;

import suaescola.suaescola.model.Curso;
import suaescola.suaescola.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/cursos") // rota administrativa
public class CursoWebController {

    private final CursoRepository repo;
    public CursoWebController(CursoRepository repo){ this.repo = repo; }

    @GetMapping
    public String listar(Model m){
        m.addAttribute("cursos", repo.findAll());
        return "cursos/listar";
    }

    @GetMapping("/novo")
    public String novoForm(Model m){
        m.addAttribute("curso", new Curso());
        return "cursos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Curso curso, BindingResult br){
        if(br.hasErrors()) return "cursos/form";
        repo.save(curso);
        return "redirect:/admin/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model m){
        Curso c = repo.findById(id).orElseThrow();
        m.addAttribute("curso", c);
        return "cursos/form";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable Long id){
        repo.deleteById(id);
        return "redirect:/admin/cursos";
    }
}
