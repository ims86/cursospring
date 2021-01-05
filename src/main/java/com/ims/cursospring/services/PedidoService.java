package com.ims.cursospring.services;

import com.ims.cursospring.domain.Pedido;
import com.ims.cursospring.repositories.PedidoRepository;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido findById (Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Pedido.class.getName()));

    }
}
