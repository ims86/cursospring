package com.ims.cursospring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ims.cursospring.domain.enums.StatusPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoBoleto extends Pagamento{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dtPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dtVencimento;

    public PagamentoBoleto(){}

    public PagamentoBoleto(Integer id, StatusPagamento status, Pedido pedido, Date dtPagamento, Date dtVencimento) {
        super(id, status, pedido);
        this.dtPagamento = dtPagamento;
        this.dtVencimento = dtVencimento;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }
}
