package github.com.petersonsource.rest.controller;

import github.com.petersonsource.domain.entity.ItemPedido;
import github.com.petersonsource.domain.entity.Pedido;
import github.com.petersonsource.domain.enums.StatusPedido;
import github.com.petersonsource.rest.dto.AtualizacaoStatusPedidoDTO;
import github.com.petersonsource.rest.dto.InformacaoItemPedidoDTO;
import github.com.petersonsource.rest.dto.InformacoesPedidoDTO;
import github.com.petersonsource.rest.dto.PedidoDTO;
import github.com.petersonsource.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedidoSalvo = service.salvar(dto);
        return pedidoSalvo.getId();

    }

    @RequestMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
            String novoStatus = dto.getNovoStatus();
            service.atualizaStatus(id, StatusPedido.valueOf(novoStatus) );
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus())
                .itens(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens
                .stream()
                .map( itemPedido ->
                    InformacaoItemPedidoDTO
                            .builder()
                            .descricaoProduto(itemPedido.getProduto().getDescricao())
                            .precoUnitario(itemPedido.getProduto().getPreco().toString())
                            .quantidade(String.valueOf(itemPedido.getQuantidade()))
                            .build()
                ).collect(Collectors.toList());
    }

}
