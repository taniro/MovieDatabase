package ufrn.br.moviedatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String titulo;
    String diretor;
    String lancamento;
    String imagemUrl;
    String sinopse;
    Integer avaliacao;
}
