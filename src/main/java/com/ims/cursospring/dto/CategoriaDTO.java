package com.ims.cursospring.dto;

import com.ims.cursospring.domain.Categoria;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull
    @Min(value = 5, message = "Tamanho mínimo de 5 caracteres")
    @Max(value = 80, message = "Tamanho máximo de 80 caracteres")
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();
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
}
