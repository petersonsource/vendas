package github.com.petersonsource;

import github.com.petersonsource.domain.entity.Cliente;
import github.com.petersonsource.domain.entity.Pedido;
import github.com.petersonsource.domain.repository.Clientes;
import github.com.petersonsource.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            Cliente cliente  = new Cliente();
            cliente.setNome("Fulano");
            Cliente clienteSaved = clientes.save(cliente);
            System.out.println(clienteSaved);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);

    }

}
