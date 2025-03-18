package com.beatspace.beatspace.services;

import com.beatspace.beatspace.models.Comentarios.Comentario;
import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.Like;
import com.beatspace.beatspace.models.LikeRequest;
import com.beatspace.beatspace.repository.LikeRepository;
import com.beatspace.beatspace.repository.ResenhaRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private LikeRepository repository;
    private ResenhaRepository _resenharepository;

    LikeService(LikeRepository _repository, ResenhaRepository resenhaRepository){
        repository = _repository;
        _resenharepository = resenhaRepository;
    }

    public void addLike(LikeRequest likeRequest){
        Resenha resenha = _resenharepository.findById(likeRequest.getResenhaId()).orElseThrow();
        Like like = new Like();
        like.setResenha(resenha);
        like.setUserId(likeRequest.getUserId());
        repository.save(like);
    }

    public void unlike(Long resenhaId, String userId){
        Like like = repository.findByResenhaAndUser(resenhaId,userId);
        repository.delete(like);
    }
}
