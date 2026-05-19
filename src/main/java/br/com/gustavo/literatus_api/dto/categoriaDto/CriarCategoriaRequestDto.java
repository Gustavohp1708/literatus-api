package br.com.gustavo.literatus_api.dto.categoriaDto;

import jakarta.validation.constraints.NotBlank;

public record CriarCategoriaRequestDto(

        Long id,
        @NotBlank
        String nome
) {
}
