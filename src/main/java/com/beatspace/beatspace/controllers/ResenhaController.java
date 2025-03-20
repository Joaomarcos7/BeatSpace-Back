package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.dto.ResenhaResponse;
import com.beatspace.beatspace.services.ResenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenhas")
public class ResenhaController {


    ResenhaService ResenhaService;

    public ResenhaController(ResenhaService _ResenhaService){
        ResenhaService = _ResenhaService;
    }

    @GetMapping
    public List<ResenhaResponse> listarTodos() {
        return ResenhaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenhaResponse> buscarPorId(@PathVariable Long id) {
        ResenhaResponse Resenha = ResenhaService.buscarPorId(id).orElseThrow().toResenhaResponse();
        return ResponseEntity.ok(Resenha);
    }


    @GetMapping("/parent/{id}")
    public ResponseEntity<List<ResenhaResponse>> getResenhasByAlbumOrPlaylist(@PathVariable String id) {
        List<ResenhaResponse> Resenhas = ResenhaService.buscarporParentId(id);
        return ResponseEntity.ok(Resenhas);
    }

    @GetMapping("/averageById/{id}")
    public ResponseEntity<Float> getAverageGradeById(@PathVariable("id") String id){
        float average =  ResenhaService.getAverageGrade(id);
        return ResponseEntity.ok(average);
    }


    @PostMapping
    public Resenha salvar(@RequestBody Resenha Resenha) {
        return ResenhaService.salvar(Resenha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resenha> atualizar(@PathVariable Long id, @RequestBody Resenha novoResenha) {
        return ResenhaService.buscarPorId(id)
                .map(ResenhaExistente -> {
                    ResenhaExistente.setTexto(novoResenha.getTexto());
                    ResenhaExistente.setAutor(novoResenha.getAutor());
                    Resenha atualizado = ResenhaService.salvar(ResenhaExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getByUser(@PathVariable("id") String id){
        return ResponseEntity.ok(this.ResenhaService.listarPorUsuario(id));
    }

    @GetMapping("/genres")
    public ResponseEntity<List<String>> getGenres(){
        return ResponseEntity.ok(this.ResenhaService.getGenres());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ResenhaResponse>> getRankings(@RequestParam("genre") String genre){
        return ResponseEntity.ok(ResenhaService.getBestGrades(genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (ResenhaService.buscarPorId(id).isPresent()) {
            ResenhaService.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/comentarios")
    public ResponseEntity<?> getComentariosByResenha(@RequestParam("resenha_id") Long resenha_id){
        return  ResponseEntity.ok(this.ResenhaService.getCommentsByResenha(resenha_id));
    }

    @GetMapping("/most-liked")
    public ResponseEntity<?> getResenhaMaisCurtidas(){
        return ResponseEntity.ok(this.ResenhaService.getResenhaMaisCurtidas());
    }

}
