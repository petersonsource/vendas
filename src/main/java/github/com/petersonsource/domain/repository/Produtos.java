package github.com.petersonsource.domain.repository;

import github.com.petersonsource.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
