package com.ims.cursospring.resources;

import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.dto.ClienteDTO;
import com.ims.cursospring.dto.ClienteNewDTO;
import com.ims.cursospring.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    ClienteService clienteService;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){

        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        //Converte a lista de clientes em lista de clientes dto
        List<ClienteDTO> clienteDTOS = clientes.stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @RequestMapping(value = "/page", method= RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPadinated(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "24")Integer linesPage,
            @RequestParam(value = "orderBy", defaultValue = "nome")String orderBy,
            @RequestParam(value = "direcion", defaultValue = "ASC")String direcion){

        Page<Cliente> clientes = clienteService.findPaginated(page, linesPage, orderBy, direcion);
        //Converte a lista de cliente em lista de clienteDTO
        Page<ClienteDTO> clienteDTOS = clientes.map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(clienteDTOS);
    }

    @RequestMapping(value = "/save", method=RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody ClienteNewDTO clienteNewDTO){
        Cliente cliente = clienteService.fromDTO(clienteNewDTO);
        cliente = clienteService.save(cliente);
        //Obtem a uri do registro salvo
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/update/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente = clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
