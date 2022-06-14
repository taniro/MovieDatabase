package ufrn.br.moviedatabase.service;

import org.springframework.stereotype.Service;
import ufrn.br.moviedatabase.repository.UsuarioRepository;


@Service
public class UsuarioService {
    UsuarioRepository repository;

    UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }
}
