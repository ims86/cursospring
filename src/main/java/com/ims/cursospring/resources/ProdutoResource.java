package com.ims.cursospring.resources;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.domain.Pedido;
import com.ims.cursospring.domain.Produto;
import com.ims.cursospring.dto.CategoriaDTO;
import com.ims.cursospring.dto.ProdutoDTO;
import com.ims.cursospring.resources.utils.URL;
import com.ims.cursospring.services.PedidoService;
import com.ims.cursospring.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Produto> findById(@PathVariable Integer id){
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPadinated(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "24")Integer linesPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
            @RequestParam(value = "direcion", defaultValue = "ASC")String direcion){
        String nomeDecode = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> produtos = produtoService.search(nomeDecode, ids,page, linesPage, orderBy, direcion);
        //Converte a lista de produto em lista de produtoDTO
        Page<ProdutoDTO> produtoDTOS = produtos.map(produto -> new ProdutoDTO(produto));
        return ResponseEntity.ok().body(produtoDTOS);
    }

}
