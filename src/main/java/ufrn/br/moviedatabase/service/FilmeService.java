package ufrn.br.moviedatabase.service;

import org.springframework.stereotype.Service;
import ufrn.br.moviedatabase.domain.Filme;
import ufrn.br.moviedatabase.repository.FilmeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public Filme create(Filme f){
        return repository.save(f);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Filme update(Filme f){
        return repository.saveAndFlush(f);
    }

    public Filme findById(Long id){
        Optional<Filme> FilmeOptional = repository.findById(id);
        return FilmeOptional.orElse(null);
    }

    public List<Filme> findAll(){
        return repository.findAll();
    }
}
