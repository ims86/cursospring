package com.ims.cursospring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    @JsonBackReference //Add para corrigir referencia ciclica ao buscar categoria
    @ManyToMany
    @JoinTable(
            name="PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    //Acima mapeamento da lista de categorias informando a tabela que junta elas
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "id.produto")
    private Set<PedidoItem> pedidoItens = new HashSet<>();

    public Produto() {
    }

    public Produto(Integer id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public List<Pedido> getPedidos(){
        List<Pedido> listaPedidos = new ArrayList<>();
        for(PedidoItem x : pedidoItens){
            listaPedidos.add(x.getPedido());
        }
        return listaPedidos;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Set<PedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public void setPedidoItens(Set<PedidoItem> pedidoItens) {
        this.pedidoItens = pedidoItens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
