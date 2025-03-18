package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Comentarios.Comentario;
import com.beatspace.beatspace.models.dto.ComentarioRequest;
import com.beatspace.beatspace.services.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    ComentarioService _comentarioService;

    public ComentarioController(ComentarioService comentarioService){
        _comentarioService = comentarioService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addComentario(@RequestBody ComentarioRequest comentariorequest){
       return ResponseEntity.ok(this._comentarioService.addComentario(comentariorequest));
    }

}
