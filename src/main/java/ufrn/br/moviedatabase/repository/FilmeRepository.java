package ufrn.br.moviedatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ufrn.br.moviedatabase.domain.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
