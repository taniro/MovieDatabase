package ufrn.br.moviedatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ufrn.br.moviedatabase.domain.Filme;
import ufrn.br.moviedatabase.repository.FilmeRepository;
import ufrn.br.moviedatabase.repository.UsuarioRepository;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class MovieDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieDatabaseApplication.class, args);
    }

    @Autowired
    private FilmeRepository filmeRepository;

    //hello
    @PostConstruct
    public void initFilmes() {

        List<Filme> filmes = Stream.of(
                new Filme(1L, "Titulo", "Diretor", "13/06/2022", "imagem", "sinopse", 5),
                new Filme(2L, "Titulo", "Diretor", "13/06/2022", "imagem", "sinopse", 5),
                new Filme(3L, "Titulo", "Diretor", "13/06/2022", "imagem", "sinopse", 5)

        ).collect(Collectors.toList());

        filmeRepository.saveAll(filmes);
    }
}
