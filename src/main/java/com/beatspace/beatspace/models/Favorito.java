package com.beatspace.beatspace.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuarioId;
    private String albumId;

    public Favorito(){}

    public Favorito(String usuarioId, String albumId){
        this.usuarioId = usuarioId;
        this.albumId = albumId;
    }

    public String getAlbumId() { // Adicionando manualmente
        return albumId;
    }

}
