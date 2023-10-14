package github.com.petersonsource;

import github.com.petersonsource.domain.entity.Cliente;
import github.com.petersonsource.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired Clientes clientes) {
        return args -> {
            System.out.println("salvando clientes");
            clientes.salvar(new Cliente("Douglas"));
            clientes.salvar(new Cliente("Igor"));

            System.out.println("listando clientes");
            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("atualizando clientes");
            System.out.println();
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.atualizar(c);
            });

            todosClientes.forEach(System.out::println);

            System.out.println("buscando cliente por nome");
            clientes.buscarPorNome("Do").forEach(System.out::println);

            System.out.println("deletando cliente");
            clientes.obterTodos().forEach(c -> {
                clientes.deletar(c);
            });

            todosClientes = clientes.obterTodos();
            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado ");
            } else {
                todosClientes.forEach(System.out::println);
            }


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }

}
