package br.com.gustavo.literatus_api.controller;

import br.com.gustavo.literatus_api.dto.categoriaDto.CategoriaResponse;
import br.com.gustavo.literatus_api.dto.categoriaDto.CriarCategoriaRequestDto;
import br.com.gustavo.literatus_api.service.ServiceCategoria;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final ServiceCategoria serviceCategoria;

    public CategoriaController(ServiceCategoria serviceCategoria) {
        this.serviceCategoria = serviceCategoria;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria (@RequestBody @Valid CriarCategoriaRequestDto request) {

        CategoriaResponse response = serviceCategoria.criarCategoria(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponse>> listarCategorias (Pageable pageable) {

        var response = serviceCategoria.listarCategorias(pageable);

        return ResponseEntity.ok(response);
    }
}
