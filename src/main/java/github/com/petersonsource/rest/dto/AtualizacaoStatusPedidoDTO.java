package github.com.petersonsource.rest.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.metamodel.StaticMetamodel;

@Getter
@Setter
public class AtualizacaoStatusPedidoDTO {

    private String novoStatus;

}
