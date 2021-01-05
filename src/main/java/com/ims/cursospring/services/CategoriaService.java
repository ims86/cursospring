package com.ims.cursospring.services;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.repositories.CategoriaRepository;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria findById (Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Categoria.class.getName()));

    }
}
