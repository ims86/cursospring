package com.ims.cursospring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ims.cursospring.domain.enums.StatusPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    //Usa o mesmo id do pedido
    private Pedido pedido;

    public Pagamento(){}

    public Pagamento(Integer id, StatusPagamento status, Pedido pedido) {
        this.id = id;
        this.status = status == null ? null : status.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusPagamento getStatus() {
        return StatusPagamento.toEnum(status);
    }

    public void setStatus(StatusPagamento status) {
        this.status = status.getCod();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
