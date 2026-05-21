package br.com.gustavo.literatus_api.controller;

import br.com.gustavo.literatus_api.dto.livroDto.AlterarLivroRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gustavo.literatus_api.dto.livroDto.CriarLivroRequestDto;
import br.com.gustavo.literatus_api.dto.livroDto.LivroResponseDto;
import br.com.gustavo.literatus_api.service.ServiceLivro;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private ServiceLivro serviceLivro;

    @PostMapping
    public ResponseEntity<LivroResponseDto> cadastrarLivro (@RequestBody @Valid CriarLivroRequestDto request) {
        LivroResponseDto livro = serviceLivro.cadastrarLivro(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(livro);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponseDto>> ConsultarLivros (Pageable pageable) {

        var response = serviceLivro.ConsultarLivros(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> ConsultarLivrosPorId (@PathVariable Long id) {
        return ResponseEntity.ok(serviceLivro.ConsultarLivroPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> alterarLivro (
            @PathVariable Long id,
            @RequestBody @Valid AlterarLivroRequestDto request
    ) {
        LivroResponseDto response = serviceLivro.alterarLivro(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        serviceLivro.deletarLivroPorId(id);
        return  ResponseEntity.noContent().build();
    }
}
