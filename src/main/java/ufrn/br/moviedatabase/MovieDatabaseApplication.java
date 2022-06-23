package ufrn.br.moviedatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.CacheControl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ufrn.br.moviedatabase.domain.Filme;
import ufrn.br.moviedatabase.domain.Usuario;
import ufrn.br.moviedatabase.repository.FilmeRepository;
import ufrn.br.moviedatabase.repository.UsuarioRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class MovieDatabaseApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MovieDatabaseApplication.class, args);
    }

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository repository;


    @PostConstruct
    public void initFilmes() {

        List<Filme> filmes = Stream.of(
                new Filme(1L, "Titulo", "Diretor", "13/06/2022", "imagem", "sinopse", 5),
                new Filme(2L, "Titulo", "Diretor", "13/06/2022", "imagem", "sinopse", 5),
                new Filme(3L, "Titulo", "Diretor", "13/06/2022", "imagem", "sinopse", 5)

        ).collect(Collectors.toList());

        filmeRepository.saveAll(filmes);

        List<Usuario> users = Stream.of(
                new Usuario(1L, "admin", encoder().encode("admin"), false, false, false, true, true),
                new Usuario(2L, "user1", encoder().encode("user1"), false, false, false, true, false),
                new Usuario(3L, "user2", encoder().encode("user2"), false, false, false, true, false)

        ).collect(Collectors.toList());

        repository.saveAll(users);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Register resource handler for images
       registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
               .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
		/*
		registry.addResourceHandler("/images/**").addResourceLocations("/images/")
		.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());*/
    }
}
