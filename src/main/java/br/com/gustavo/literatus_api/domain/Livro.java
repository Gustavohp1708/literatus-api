package br.com.gustavo.literatus_api.domain;

import br.com.gustavo.literatus_api.dto.livroDto.AlterarLivroRequestDto;
import br.com.gustavo.literatus_api.dto.livroDto.CriarLivroRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_livros")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private @NotBlank String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(unique = true, nullable = false)
    private String isbn;

    private Integer anoPublicacao;

    @ManyToOne
    @JoinColumn(nullable = false, name = "categoria_id")
    private Categoria categoria;


    public Livro(CriarLivroRequestDto request, Categoria categoria) {
        this.titulo = request.titulo();
        this.autor = request.autor();
        this.isbn = request.isbn();
        this.anoPublicacao = request.anoPublicacao();
        this.categoria = categoria;
    }

    public void alterarLivro(AlterarLivroRequestDto request, Categoria novaCategoria){

        if (request.titulo() != null && !request.titulo().isBlank()) {
            this.titulo = request.titulo();
        }

        if (request.autor() != null && !request.autor().isBlank()) {
            this.autor = request.autor();
        }

        if (request.isbn() != null && !request.isbn().isBlank()) {
            this.isbn = request.isbn();
        }

        if (request.anoPublicacao() != null) {
            this.anoPublicacao = request.anoPublicacao();
        }

        if (novaCategoria != null) {
            this.categoria = novaCategoria;
        }
    }


}
