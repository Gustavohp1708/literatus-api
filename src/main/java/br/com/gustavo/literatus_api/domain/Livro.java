package br.com.gustavo.literatus_api.domain;

import jakarta.persistence.*;
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
    Long id;
    String titulo;
    String autor;
    String isbn;
    int anoPublicacao;
    @ManyToOne
    @Column(nullable = false, name = "categoria_id")
    Categoria categoria;


    public Livro(CriarLivroRequestDto request){
        this.titulo = request.titulo();
        this.titulo = request.autor();
        this.titulo = request.isbn();
        this.titulo = request.anoPublicacao();
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
