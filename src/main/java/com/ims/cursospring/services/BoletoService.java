package com.ims.cursospring.services;

import com.ims.cursospring.domain.PagamentoBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencerPagtoBoleto(PagamentoBoleto pagto, Date dataPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataPedido);
        cal.add(Calendar.DAY_OF_MONTH,7);
        pagto.setDtVencimento(cal.getTime());
    }
}
