package br.com.gustavo.literatus_api.domain;

import br.com.gustavo.literatus_api.dto.categoriaDto.CriarCategoriaRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_categorias")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String nome;

    public Categoria(CriarCategoriaRequestDto request){
        this.nome = request.nome();
    }


}
