package com.ims.cursospring.services;

import com.ims.cursospring.domain.Cidade;
import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.domain.Endereco;
import com.ims.cursospring.domain.enums.TipoCliente;
import com.ims.cursospring.dto.ClienteDTO;
import com.ims.cursospring.dto.ClienteNewDTO;
import com.ims.cursospring.repositories.ClienteRepository;
import com.ims.cursospring.repositories.EnderecoRepository;
import com.ims.cursospring.services.exceptions.DataIntegrityException;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente findById (Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Cliente.class.getName()));

    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPaginated(Integer page, Integer linesPage, String orderBy, String direcion){
        PageRequest pageRequest = PageRequest.of(page,linesPage, Sort.Direction.valueOf(direcion), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    @Transactional
    public Cliente save(Cliente cliente){
        cliente = clienteRepository.save(cliente);
        enderecoRepository.save(cliente.getEnderecos().get(0));
        return cliente;
    }

    public Cliente update(Cliente cliente){
        Cliente cli = findById(cliente.getId());
        updateData(cli, cliente);
        return clienteRepository.save(cli);
    }

    public void delete(Integer id){
        findById(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cliente possui pedidos vinculados. Não é possivél excluir.");
        }
    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO clienteNewDTO){
        Cliente cli = new Cliente(null,
                clienteNewDTO.getNome(),
                clienteNewDTO.getEmail(),
                clienteNewDTO.getCpfOrCnpj(),
                TipoCliente.toEnum(clienteNewDTO.getTipoCliente()));
        Cidade cid = new Cidade(clienteNewDTO.getCidadeId(),null,null);
        Endereco end = new Endereco(
                null,
                clienteNewDTO.getLogradouro(),
                clienteNewDTO.getNumero(),
                clienteNewDTO.getComplemento(),
                clienteNewDTO.getBairro(),
                clienteNewDTO.getCep(),
                cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(clienteNewDTO.getFone1());
        if(clienteNewDTO.getFone2()!=null){
            cli.getTelefones().add(clienteNewDTO.getFone2());
        }
        if(clienteNewDTO.getFone3()!=null){
            cli.getTelefones().add(clienteNewDTO.getFone3());
        }
        return cli;

    }

    private void updateData(Cliente clienteBD, Cliente cliente){
        clienteBD.setNome(cliente.getNome());
        clienteBD.setEmail(cliente.getEmail());
    }
}
