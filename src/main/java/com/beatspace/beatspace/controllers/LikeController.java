package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.Like;
import com.beatspace.beatspace.models.LikeRequest;
import com.beatspace.beatspace.services.EmailService;
import com.beatspace.beatspace.services.LikeService;
import com.beatspace.beatspace.services.ResenhaService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    LikeService _likeService;
    ResenhaService _resenhaService;

    EmailService _emailService;

    public LikeController(LikeService likeService,ResenhaService resenhaService,EmailService emailservice){
        _likeService = likeService;
        _resenhaService = resenhaService;
        _emailService = emailservice;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLike(@RequestBody LikeRequest likeRequest) throws MessagingException {

        this._likeService.addLike(likeRequest);
        //this._emailService.sendEmail("joao.chaves@academico.ifpb.edu.br", "Sua resenha esá bombando!", "Olá João Marcos Dantas de Souza Chaves ,Sua resenha está sendo bastante curtida, entre no beatspace.com.br e verifique quem está curtindo seu post e interaja com novos usuários");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<?> unlike(@RequestParam("resenhaId") Long resenhaId, @RequestParam("userId") String userId){
        this._likeService.unlike(resenhaId, userId);
        return ResponseEntity.ok().build();
    }



}
