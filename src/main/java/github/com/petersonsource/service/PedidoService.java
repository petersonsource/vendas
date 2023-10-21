package github.com.petersonsource.service;

import github.com.petersonsource.domain.entity.Pedido;
import github.com.petersonsource.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

}
