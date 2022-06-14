package ufrn.br.moviedatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ufrn.br.moviedatabase.domain.Filme;
import ufrn.br.moviedatabase.service.FilmeService;

import java.util.List;

@Controller
public class FilmeController {
    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }



    @GetMapping("/")
    public String getFilmesHome(Model model){

        List<Filme> filmes = service.findAll();
        model.addAttribute("filmes", filmes);

        return "index";
    }

    @GetMapping("/cadastrarFilme")
    public String getFilmesCadastro(Model model){
        Filme f = new Filme();
        model.addAttribute("filme", f);
        return "produto/cadastrar";
    }

    @GetMapping("editar/{id}")
    public String getFilmesHome(Model model, @PathVariable Long id){

        Filme filme = service.findById(id);
        model.addAttribute("filme", filme);
        return "produto/editar";
    }

    @GetMapping("deletar/{id}")
    public String doDeletarFilme(@PathVariable Long id){
        service.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("salvar")
    public String doSalvaFilme(@ModelAttribute Filme f){
       service.update(f);
        return "redirect:/";
    }
}
