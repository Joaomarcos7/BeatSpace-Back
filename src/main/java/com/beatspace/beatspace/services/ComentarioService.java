package com.beatspace.beatspace.services;

import com.beatspace.beatspace.models.Comentario;
import com.beatspace.beatspace.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public Comentario salvar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deletar(Long id) {
        comentarioRepository.deleteById(id);
    }

    public List<Comentario> buscarporParentId(String id){return comentarioRepository.findByParentId(id);}
}
