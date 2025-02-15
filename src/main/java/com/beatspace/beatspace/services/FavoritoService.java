package com.beatspace.beatspace.services;


import com.beatspace.beatspace.models.Favorito;
import com.beatspace.beatspace.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRepository;


    public void favoritarAlbum(String usuarioId, String albumId) {
        boolean jaFavoritado = favoritoRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId).isPresent();
        if (!jaFavoritado) {
            Favorito favorito = new Favorito(usuarioId, albumId);
            favoritoRepository.save(favorito);
        } else {
            throw new RuntimeException("Álbum já está favoritado");
        }
    }

    public void desfavoritarAlbum(String usuarioId, String albumId) {
        favoritoRepository.findByUsuarioIdAndAlbumId(usuarioId, albumId)
                .ifPresent(favoritoRepository::delete);
    }

    public List<String> listarFavoritos(String usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(favorito -> favorito.getAlbumId()) // <- Alteração aqui
                .collect(Collectors.toList());
    }


}
