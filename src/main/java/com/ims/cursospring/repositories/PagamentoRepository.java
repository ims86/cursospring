package com.ims.cursospring.repositories;

import com.ims.cursospring.domain.Pagamento;
import com.ims.cursospring.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
