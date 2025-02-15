package com.beatspace.beatspace.controllers;

import com.beatspace.beatspace.services.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;
    @PostMapping("/{usuarioId}/{albumId}")
    public ResponseEntity<String> favoritarAlbum(@PathVariable String usuarioId, @PathVariable String albumId) {
        favoritoService.favoritarAlbum(usuarioId, albumId);
        return ResponseEntity.ok("Álbum favoritado com sucesso!");
    }

    @DeleteMapping("/{usuarioId}/{albumId}")
    public ResponseEntity<String> desfavoritarAlbum(@PathVariable String usuarioId, @PathVariable String albumId) {
        favoritoService.desfavoritarAlbum(usuarioId, albumId);
        return ResponseEntity.ok("Álbum removido dos favoritos!");
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<String>> listarFavoritos(@PathVariable String usuarioId) {
        List<String> favoritos = favoritoService.listarFavoritos(usuarioId);
        return ResponseEntity.ok(favoritos);
    }
}
