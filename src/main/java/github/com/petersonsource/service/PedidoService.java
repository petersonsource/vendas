package github.com.petersonsource.service;

import github.com.petersonsource.domain.entity.Pedido;
import github.com.petersonsource.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
