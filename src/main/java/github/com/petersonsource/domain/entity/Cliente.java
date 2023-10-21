package github.com.petersonsource.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome",  length = 100)
    @NotEmpty(message = "campo nome é obrigatório")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "campo cpf é obrigatorio")
    @CPF(message = "informe um cpf válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente",  fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

}
