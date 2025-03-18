package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.Like;
import com.beatspace.beatspace.models.LikeRequest;
import com.beatspace.beatspace.services.LikeService;
import com.beatspace.beatspace.services.ResenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    LikeService _likeService;
    ResenhaService _resenhaService;

    public LikeController(LikeService likeService,ResenhaService resenhaService){
        _likeService = likeService;
        _resenhaService = resenhaService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLike(@RequestBody LikeRequest likeRequest){

        this._likeService.addLike(likeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<?> unlike(@RequestParam("resenhaId") Long resenhaId, @RequestParam("userId") String userId){
        this._likeService.unlike(resenhaId, userId);
        return ResponseEntity.ok().build();
    }



}
