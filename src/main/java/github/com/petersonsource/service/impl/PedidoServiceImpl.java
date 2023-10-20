package github.com.petersonsource.service.impl;

import github.com.petersonsource.domain.entity.Cliente;
import github.com.petersonsource.domain.entity.ItemPedido;
import github.com.petersonsource.domain.entity.Pedido;
import github.com.petersonsource.domain.entity.Produto;
import github.com.petersonsource.domain.repository.Clientes;
import github.com.petersonsource.domain.repository.ItemsPedido;
import github.com.petersonsource.domain.repository.Pedidos;
import github.com.petersonsource.domain.repository.Produtos;
import github.com.petersonsource.exception.RegraNegocioException;
import github.com.petersonsource.rest.dto.ItemPedidoDTO;
import github.com.petersonsource.rest.dto.PedidoDTO;
import github.com.petersonsource.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRespository;
    private final Produtos produtosRespository;
    private final ItemsPedido itemsPedidoRespository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRespository.findById(idCliente)
                .orElseThrow( () -> new RegraNegocioException("codigo de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        List<ItemPedido> itensPedido = converterItems(pedido, dto.getItens());

       repository.save(pedido);
       itemsPedidoRespository.saveAll(itensPedido);
       pedido.setItens(itensPedido);
       return pedido;

    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itens){
        if(itens.isEmpty()){
            throw  new RegraNegocioException("não é possível realizar um pedido sem itens");
        }

        return itens.
                stream().
                map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRespository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "código de produto inválildo: " +idProduto
                            ));


                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
