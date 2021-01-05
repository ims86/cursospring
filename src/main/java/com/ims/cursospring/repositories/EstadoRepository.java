package com.ims.cursospring.repositories;

import com.ims.cursospring.domain.Estado;
import com.ims.cursospring.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
