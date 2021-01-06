package com.ims.cursospring.services.validation;

import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.domain.enums.TipoCliente;
import com.ims.cursospring.dto.ClienteNewDTO;
import com.ims.cursospring.repositories.ClienteRepository;
import com.ims.cursospring.resources.exception.FieldMessageError;
import com.ims.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessageError> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessageError("cpfOuCnpj","CPF inválido"));
        } else if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessageError("cpfOuCnpj","CNPJ inválido"));
        }

        Cliente aux = clienteRepository.findByEmal(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessageError("email","Email já cadastrado"));
        }

        for (FieldMessageError e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addNode(e.getFieldname()).addConstraintViolation();
//            context.buildConstraintViolationWithTemplate(e.getMessage())
//                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}