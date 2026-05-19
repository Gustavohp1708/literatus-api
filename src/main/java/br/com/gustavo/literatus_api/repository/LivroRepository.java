package br.com.gustavo.literatus_api.repository;

import br.com.gustavo.literatus_api.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
