package github.com.petersonsource.domain.repository;

import github.com.petersonsource.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = "delete from Cliente c where c.nome = ':nome' ")
    @Modifying
    void deleteByNome(@Param("nome") String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    boolean existsByNome(String nome);

    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


}
