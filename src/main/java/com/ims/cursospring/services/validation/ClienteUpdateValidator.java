package com.ims.cursospring.services.validation;

import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.domain.enums.TipoCliente;
import com.ims.cursospring.dto.ClienteDTO;
import com.ims.cursospring.dto.ClienteNewDTO;
import com.ims.cursospring.repositories.ClienteRepository;
import com.ims.cursospring.resources.exception.FieldMessageError;
import com.ims.cursospring.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String,String> map = (Map<String,String>)httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        List<FieldMessageError> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        Cliente aux = clienteRepository.findByEmal(objDto.getEmail());
        if(aux != null && !aux.getId().equals(Integer.parseInt(map.get("id")))){
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