package com.ims.cursospring.services;

import com.ims.cursospring.domain.Categoria;
import com.ims.cursospring.domain.Cliente;
import com.ims.cursospring.dto.CategoriaDTO;
import com.ims.cursospring.repositories.CategoriaRepository;
import com.ims.cursospring.services.exceptions.DataIntegrityException;
import com.ims.cursospring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria findById (Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException(
                        "objeto não encontrado! Id: " +
                        id + ", Tipo: " +
                        Categoria.class.getName()));

    }
    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPaginated(Integer page, Integer linesPage, String orderBy, String direcion){
        PageRequest pageRequest = PageRequest.of(page,linesPage, Sort.Direction.valueOf(direcion), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        findById(categoria.getId());
        Categoria cat = findById(categoria.getId());
        updateData(cat, categoria);
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id){
        findById(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Categoria possui vinculo com produtos. Não é possivél excluir.");
        }
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    private void updateData(Categoria CategoriaBD, Categoria categoria){
        CategoriaBD.setNome(categoria.getNome());
    }
}
