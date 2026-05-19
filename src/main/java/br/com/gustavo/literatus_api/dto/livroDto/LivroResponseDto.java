package br.com.gustavo.literatus_api.dto.livroDto;

import br.com.gustavo.literatus_api.domain.Categoria;
import br.com.gustavo.literatus_api.domain.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroResponseDto(

        Long id,
        String titulo,
        String autor,
        String isbn,
        Integer anoPublicacao,
        Categoria categoria
) {

    public LivroResponseDto(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getCategoria());
    }
}
