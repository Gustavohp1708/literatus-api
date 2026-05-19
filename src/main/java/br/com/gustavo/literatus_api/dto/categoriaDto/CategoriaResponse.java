package br.com.gustavo.literatus_api.dto.categoriaDto;

import br.com.gustavo.literatus_api.domain.Categoria;

public record CategoriaResponse(
        String nome
) {
    public CategoriaResponse(Categoria categoria) {
        this(categoria.getNome());
    }
}
