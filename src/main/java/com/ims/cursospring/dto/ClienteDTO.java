package com.ims.cursospring.dto;

import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.services.validation.ClienteUpdate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "Nome obrigatório")
    @Size(min = 5, max = 100, message = "Nome deve ter mínimo 5 e máximo 100 caracteres")
    private String nome;

    @NotNull(message = "Email obrigatório")
    private String email;

    public ClienteDTO(){}

    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
