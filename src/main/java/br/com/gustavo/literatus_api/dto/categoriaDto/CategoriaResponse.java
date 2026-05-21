package br.com.gustavo.literatus_api.dto.categoriaDto;

import br.com.gustavo.literatus_api.domain.Categoria;

public record CategoriaResponse(
        Long id,
        String nome
) {
    public CategoriaResponse(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome()
                );

    }
}
