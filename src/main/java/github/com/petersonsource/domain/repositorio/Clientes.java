package github.com.petersonsource.domain.repositorio;

import github.com.petersonsource.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ? ";
    private static String DELETE =  "DELETE FROM CLIENTE WHERE ID = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id){
        Cliente cliente = entityManager.find( Cliente.class, id );
        deletar(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
        String jpql = " select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos(){
        return entityManager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
