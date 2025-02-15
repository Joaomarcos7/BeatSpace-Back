package com.beatspace.beatspace.repository;

import com.beatspace.beatspace.models.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito,Long> {
    List<Favorito> findByUsuarioId(String usuarioId);
    Optional<Favorito> findByUsuarioIdAndAlbumId(String usuarioId, String albumId);

}
