package br.com.gustavo.literatus_api.dto.livroDto;

import br.com.gustavo.literatus_api.domain.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlterarLivroRequestDto(


        Long id,
        String titulo,
        String autor,
        String isbn,
        Integer anoPublicacao,
        @JsonProperty("categoriaId")
        Long categoriaId
) {
}
