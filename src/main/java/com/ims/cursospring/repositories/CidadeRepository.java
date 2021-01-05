package com.ims.cursospring.repositories;

import com.ims.cursospring.domain.Cidade;
import com.ims.cursospring.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
