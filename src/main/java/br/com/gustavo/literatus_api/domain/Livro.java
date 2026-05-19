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


    public Livro(CriarLivroRequestDto request){
        this.titulo = request.titulo();
        this.autor = request.autor();
        this.isbn = request.isbn();
        this.anoPublicacao = request.anoPublicacao();
        this.categoria = new Categoria(request.categoria());
    }

    public void alterarLivro(AlterarLivroRequestDto request){

        if (request.titulo() != null){
            this.titulo = request.titulo();
        }

        if (request.autor() != null){
            this.autor = request.autor();
        }

        if (request.isbn() != null){
            this.isbn = request.isbn();
        }

        if (request.anoPublicacao() != null){
            this.anoPublicacao = request.anoPublicacao();
        }

        if (request.categoria() != null){
            this.categoria = request.categoria();
        }
    }
}
