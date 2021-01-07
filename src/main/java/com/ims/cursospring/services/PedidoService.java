package com.ims.cursospring.services;

import com.ims.cursospring.domain.PagamentoBoleto;
import com.ims.cursospring.domain.Pedido;
import com.ims.cursospring.domain.PedidoItem;
import com.ims.cursospring.domain.enums.StatusPagamento;
import com.ims.cursospring.repositories.PagamentoRepository;
import com.ims.cursospring.repositories.PedidoItemRepository;
import com.ims.cursospring.repositories.PedidoRepository;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    BoletoService boletoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoItemRepository pedidoItemRepository;

    public Pedido findById (Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Pedido.class.getName()));
    }

    public Pedido save(Pedido pedido){
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setStatus(StatusPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoBoleto){
            PagamentoBoleto pagto = (PagamentoBoleto) pedido.getPagamento();
            boletoService.preencerPagtoBoleto(pagto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for(PedidoItem pedidoItem : pedido.getPedidoItens()){
            pedidoItem.setDesconto(0.00);
            pedidoItem.setPreco(produtoService.findById(pedidoItem.getProduto().getId()).getPreco());
            pedidoItem.setPedido(pedido);
        }
        pedidoItemRepository.saveAll(pedido.getPedidoItens());
        return pedido;
    }

}
