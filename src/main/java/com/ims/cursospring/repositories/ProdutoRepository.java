package com.ims.cursospring.repositories;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
