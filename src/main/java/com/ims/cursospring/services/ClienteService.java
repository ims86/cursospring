package com.ims.cursospring.services;

import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.repositories.ClienteRepository;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Cliente findById (Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Cliente.class.getName()));

    }
}
