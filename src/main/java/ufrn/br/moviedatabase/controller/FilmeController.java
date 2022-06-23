package ufrn.br.moviedatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ufrn.br.moviedatabase.domain.Filme;
import ufrn.br.moviedatabase.service.FileStorageService;
import ufrn.br.moviedatabase.service.FilmeService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class FilmeController {
    private final FilmeService service;
    private final FileStorageService fileStorageService;

    public FilmeController(FilmeService service, FileStorageService fileStorageService) {
        this.service = service;
        this.fileStorageService = fileStorageService;
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
    public String doSalvaFilme(@ModelAttribute @Valid Filme f, Errors errors, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request){

        if (errors.hasErrors()){
            /**
             * TODO Implementar uma solução para retornar a página adequada (Cadastro ou Editar{id})
             */
            System.out.println(errors.getAllErrors().stream().toArray());
            return "produto/cadastrar";
        }else{
            /*
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getContentType());
			System.out.println(file.getSize());
             */
            f.setImagemUrl(file.getOriginalFilename());
            service.update(f);
            fileStorageService.save(file);

            redirectAttributes.addAttribute("msg", "Cadastro realizado com sucesso");
            return "redirect:/";
        }
    }
}
