package br.com.gustavo.literatus_api.dto.livroDto;

import br.com.gustavo.literatus_api.domain.Categoria;
import br.com.gustavo.literatus_api.domain.Livro;
import br.com.gustavo.literatus_api.dto.categoriaDto.CategoriaResponse;
import jakarta.validation.constraints.NotBlank;

public record LivroResponseDto(

        Long id,
        String titulo,
        String autor,
        String isbn,
        Integer anoPublicacao,
        CategoriaResponse categoria
) {

    public LivroResponseDto(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                new CategoriaResponse(livro.getCategoria())
        );
    }
}
