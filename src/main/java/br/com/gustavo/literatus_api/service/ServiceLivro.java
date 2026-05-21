package br.com.gustavo.literatus_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gustavo.literatus_api.domain.Categoria;
import br.com.gustavo.literatus_api.domain.Livro;
import br.com.gustavo.literatus_api.dto.livroDto.AlterarLivroRequestDto;
import br.com.gustavo.literatus_api.dto.livroDto.CriarLivroRequestDto;
import br.com.gustavo.literatus_api.dto.livroDto.LivroResponseDto;
import br.com.gustavo.literatus_api.repository.CategoriaRepository;
import br.com.gustavo.literatus_api.repository.LivroRepository;
import jakarta.transaction.Transactional;

@Service
public class ServiceLivro {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public LivroResponseDto cadastrarLivro(CriarLivroRequestDto dto) {

        if (dto.categoriaId() == null) {
            throw new IllegalArgumentException("O ID da categoria não pode ser nulo!");
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + dto.categoriaId()));

        Livro livro = new Livro(dto, categoria);

        livro = livroRepository.save(livro);

        return new LivroResponseDto(livro);
    }

    @Transactional
    public LivroResponseDto alterarLivro(Long id, AlterarLivroRequestDto dto) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        Categoria novaCategoria = null;

        if (dto.categoriaId() != null) {
            novaCategoria = categoriaRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + dto.categoriaId()));
        }

        livro.alterarLivro(dto, novaCategoria);

        return new LivroResponseDto(livro);
    }

    public Page<LivroResponseDto> ConsultarLivros (Pageable pageable){
        return livroRepository.findAll(pageable).map(LivroResponseDto::new);
    }

    public LivroResponseDto ConsultarLivroPorId (Long id){
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro de ID: " + id +" não encontrado"));
        return new LivroResponseDto(livro);
    }

    @Transactional
    public void deletarLivroPorId(Long id){
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro de ID: " + id + " não encontrado"));

        livroRepository.delete(livro);

    }
}
