package br.com.gustavo.literatus_api.repository;

import br.com.gustavo.literatus_api.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
