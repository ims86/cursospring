package com.ims.cursospring.services;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.domain.Pedido;
import com.ims.cursospring.domain.Produto;
import com.ims.cursospring.repositories.CategoriaRepository;
import com.ims.cursospring.repositories.PedidoRepository;
import com.ims.cursospring.repositories.ProdutoRepository;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Produto findById (Integer id){
        Optional<Produto> obj = repository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPage, String orderBy, String direcion){
        PageRequest pageRequest = PageRequest.of(page,linesPage, Sort.Direction.valueOf(direcion), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
//        return repository.findDistinctByNomeCotainingAndCategoriasIn(nome, categorias, pageRequest);
        return repository.search(nome, categorias, pageRequest);
    }

}
