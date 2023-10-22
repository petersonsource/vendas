package github.com.petersonsource.domain.repository;

import github.com.petersonsource.domain.entity.Usuario;
import github.com.petersonsource.service.impl.UsuarioServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

}
