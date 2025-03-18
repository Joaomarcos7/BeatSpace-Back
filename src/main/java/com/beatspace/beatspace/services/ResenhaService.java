package com.beatspace.beatspace.services;

import com.beatspace.beatspace.models.Comentarios.Comentario;
import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.dto.ResenhaResponse;
import com.beatspace.beatspace.repository.ResenhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenhaService {

    ResenhaRepository _ResenhaRepository;
    public ResenhaService(ResenhaRepository ResenhaRepository){
        _ResenhaRepository = ResenhaRepository;
    }

    public List<ResenhaResponse> listarTodos() {
        return _ResenhaRepository.findAll().stream().map(x->x.toResenhaResponse()).toList();
    }

    public Optional<Resenha> buscarPorId(Long id) {
        return _ResenhaRepository.findById(id);
    }

    public Resenha salvar(Resenha Resenha) {
        return _ResenhaRepository.save(Resenha);
    }

    public void deletar(Long id) {
        _ResenhaRepository.deleteById(id);
    }

    public List<ResenhaResponse> buscarporParentId(String id){return _ResenhaRepository.findByParentId(id).stream().map(x->x.toResenhaResponse()).toList();}

    public float getAverageGrade(String id){
        return _ResenhaRepository.getAverageGrade(id);
    }

    public List<ResenhaResponse> getBestGrades(){return _ResenhaRepository.getBestGrades().stream().map(x->x.toResenhaResponse()).toList();}

    public int getTotalLikes(Long Resenha_id){
        return  _ResenhaRepository.getTotalLikesFromResenha(Resenha_id);
    }

    public List<Comentario> getCommentsByResenha(Long resenha_id){
        return this._ResenhaRepository.getCommentsFromResenha(resenha_id);
    }

    public List<ResenhaResponse> getResenhaMaisCurtidas(){
        return this._ResenhaRepository.getResenhaMaisCurtidas().stream().map(x->x.toResenhaResponse()).toList();
    }
}
