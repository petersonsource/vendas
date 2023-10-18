package github.com.petersonsource.domain.repository;

import github.com.petersonsource.domain.entity.Cliente;
import github.com.petersonsource.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
