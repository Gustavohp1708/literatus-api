package br.com.gustavo.literatus_api.service;

import br.com.gustavo.literatus_api.domain.Categoria;
import br.com.gustavo.literatus_api.domain.Livro;
import br.com.gustavo.literatus_api.dto.livroDto.AlterarLivroRequestDto;
import br.com.gustavo.literatus_api.dto.livroDto.CriarLivroRequestDto;
import br.com.gustavo.literatus_api.dto.livroDto.LivroResponseDto;
import br.com.gustavo.literatus_api.repository.CategoriaRepository;
import br.com.gustavo.literatus_api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ServiceLivro {

    @Autowired
    private LivroRepository livroRepository;

    private CategoriaRepository categoriaRepository;

    public LivroResponseDto cadastrarLivro (CriarLivroRequestDto request){
        Categoria categoria = categoriaRepository.findById(request.categoria().id())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + request.categoria().id()));

        Livro livro = new Livro();

        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setIsbn(request.isbn());
        livro.setAnoPublicacao(request.anoPublicacao());
        livro.setCategoria(categoria);

        livroRepository.save(livro);
        return new LivroResponseDto(livro);
    }

    public LivroResponseDto alterarLivro (AlterarLivroRequestDto request){
        var livro = livroRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Livro de ID: " + request.id() +" não encontrado"));

        livro.alterarLivro(request);

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

    public void deletarLivroPorId(Long id){
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro de ID: " + id + " não encontrado"));

        livroRepository.delete(livro);

    }
}
