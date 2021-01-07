package com.ims.cursospring.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ims.cursospring.domain.enums.StatusPagamento;

import javax.persistence.Entity;

@Entity
@JsonTypeName("PagamentoCartao")
public class PagamentoCartao extends Pagamento{
    private static final long serialVersionUID = 1L;

    private Integer numParcelas;

    public PagamentoCartao(){}

    public PagamentoCartao(Integer id, StatusPagamento status, Pedido pedido, Integer numParcelas) {
        super(id, status, pedido);
        this.numParcelas = numParcelas;
    }

    public Integer getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(Integer numParcelas) {
        this.numParcelas = numParcelas;
    }
}
