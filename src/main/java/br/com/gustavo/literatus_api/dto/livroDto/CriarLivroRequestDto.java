package br.com.gustavo.literatus_api.dto.livroDto;

import br.com.gustavo.literatus_api.dto.categoriaDto.CriarCategoriaRequestDto;
import jakarta.validation.constraints.NotBlank;

public record CriarLivroRequestDto(

        @NotBlank
        String titulo,

        @NotBlank
        String autor,

        @NotBlank
        String isbn,

        Integer anoPublicacao,

        @NotBlank
        CriarCategoriaRequestDto categoria
) {
}
