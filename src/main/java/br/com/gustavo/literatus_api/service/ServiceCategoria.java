package br.com.gustavo.literatus_api.service;

import br.com.gustavo.literatus_api.domain.Categoria;
import br.com.gustavo.literatus_api.dto.categoriaDto.CategoriaResponse;
import br.com.gustavo.literatus_api.dto.categoriaDto.CriarCategoriaRequestDto;
import br.com.gustavo.literatus_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceCategoria {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaResponse criarCategoria(CriarCategoriaRequestDto request){
        var categoria = new Categoria(request);
        repository.save(categoria);
        return new CategoriaResponse(categoria);
    }

    public Page<CategoriaResponse> listarCategorias(Pageable pageable){
        return repository.findAll(pageable).map(CategoriaResponse::new);
    }
}
