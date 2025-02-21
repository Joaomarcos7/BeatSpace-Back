package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Comentario;
import com.beatspace.beatspace.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios")
public class ComentariosController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> listarTodos() {
        return comentarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.buscarPorId(id);
        return comentario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/parent/{id}")
    public ResponseEntity<List<Comentario>> getComentariosByAlbumOrPlaylist(@PathVariable String id) {
        List<Comentario> comentarios = comentarioService.buscarporParentId(id);
        return ResponseEntity.ok(comentarios);
    }



    @PostMapping
    public Comentario salvar(@RequestBody Comentario comentario) {
        return comentarioService.salvar(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizar(@PathVariable Long id, @RequestBody Comentario novoComentario) {
        return comentarioService.buscarPorId(id)
                .map(comentarioExistente -> {
                    comentarioExistente.setTexto(novoComentario.getTexto());
                    comentarioExistente.setAutor(novoComentario.getAutor());
                    Comentario atualizado = comentarioService.salvar(comentarioExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (comentarioService.buscarPorId(id).isPresent()) {
            comentarioService.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
