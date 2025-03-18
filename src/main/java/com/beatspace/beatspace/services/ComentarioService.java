package com.beatspace.beatspace.services;


import com.beatspace.beatspace.models.Comentarios.Comentario;
import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.dto.ComentarioRequest;
import com.beatspace.beatspace.models.dto.ComentarioResponse;
import com.beatspace.beatspace.repository.ComentarioRepository;
import com.beatspace.beatspace.repository.ResenhaRepository;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    ComentarioRepository _comentarioRepository;
    ResenhaRepository _resenhaRepository;
    public ComentarioService(ComentarioRepository comentarioRepository, ResenhaRepository resenhaRepository){
        this._comentarioRepository = comentarioRepository;
        this._resenhaRepository = resenhaRepository;
    }

    public ComentarioResponse addComentario(ComentarioRequest comentariorequest){
        Resenha resenha = this._resenhaRepository.findById(comentariorequest.resenhaId()).orElseThrow();
        Comentario comentario = new Comentario();
        comentario.setData(comentariorequest.data());
        comentario.setAutor(comentariorequest.autor());
        comentario.setResenha(resenha);
        comentario.setUserimg(comentariorequest.userimg());
        comentario.setTexto(comentariorequest.texto());
        comentario.setUsername(comentariorequest.username());
        this._comentarioRepository.save(comentario);
        return comentario.toComentarioResponse();
    }



}
