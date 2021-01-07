package com.ims.cursospring.resources;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.domain.Pedido;
import com.ims.cursospring.dto.CategoriaDTO;
import com.ims.cursospring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody Pedido pedido){
        pedido = pedidoService.save(pedido);
        //Obtem a uri do registro salvo
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
