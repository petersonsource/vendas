package github.com.petersonsource.rest.controller;

import github.com.petersonsource.domain.entity.Pedido;
import github.com.petersonsource.rest.dto.PedidoDTO;
import github.com.petersonsource.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedidoSalvo = service.salvar(dto);
        return pedidoSalvo.getId();

    }


}
