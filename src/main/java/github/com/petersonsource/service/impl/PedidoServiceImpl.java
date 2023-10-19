package github.com.petersonsource.service.impl;

import github.com.petersonsource.domain.repository.Pedidos;
import github.com.petersonsource.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private Pedidos repository;


}
