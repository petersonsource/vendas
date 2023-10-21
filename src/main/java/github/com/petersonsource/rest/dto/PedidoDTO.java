package github.com.petersonsource.rest.dto;


/*
{
    "cliente" : 1,
    "total": 100,
    "itens": [
        {
            "produto": 1,
            "quantidade": 10
        }
    ]
}
 */

import github.com.petersonsource.validation.contraintValidation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "informe o codigo do cliente")
    private Integer cliente;
    @NotNull(message = "o total do pedido é obrigatorio")
    private BigDecimal total;
    @NotEmptyList(message = "pedido não popde ser realizado sem itens")
    private List<ItemPedidoDTO> itens;

}
