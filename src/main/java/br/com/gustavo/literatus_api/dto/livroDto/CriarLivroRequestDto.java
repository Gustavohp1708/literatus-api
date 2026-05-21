package br.com.gustavo.literatus_api.dto.livroDto;

import br.com.gustavo.literatus_api.dto.categoriaDto.CriarCategoriaRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarLivroRequestDto(

        @NotBlank
        String titulo,

        @NotBlank
        String autor,

        @NotBlank
        String isbn,

        Integer anoPublicacao,

        @NotNull
        @JsonProperty("categoriaId")
        Long categoriaId
) {
}
