package com.ims.cursospring.resources;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.dto.CategoriaDTO;
import com.ims.cursospring.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService categoriaService;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> categorias = categoriaService.findAll();
        //Converte a lista de categorias em lista de categorias dto
        List<CategoriaDTO> categoriaDTOS = categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @RequestMapping(value = "/page", method= RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> findPadinated(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "24")Integer linesPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
            @RequestParam(value = "direcion", defaultValue = "ASC")String direcion){

        Page<Categoria> categorias = categoriaService.findPaginated(page, linesPage, orderBy, direcion);
        //Converte a lista de categoria em lista de categoriaDTO
        Page<CategoriaDTO> categoriaDTOS = categorias.map(categoria -> new CategoriaDTO(categoria));
        return ResponseEntity.ok().body(categoriaDTOS);
    }

    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);
        categoria = categoriaService.save(categoria);
        //Obtem a uri do registro salvo
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/update/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaService.fromDTO(categoriaDTO);
        categoria = categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
