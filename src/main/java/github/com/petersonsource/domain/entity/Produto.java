package github.com.petersonsource.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "produto")

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
