package com.ims.cursospring.resources;

import com.ims.cursospring.domain.Pedido;
import com.ims.cursospring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

    @Autowired
    PedidoService pedidoService;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }

}
