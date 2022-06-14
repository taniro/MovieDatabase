package ufrn.br.moviedatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.moviedatabase.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
