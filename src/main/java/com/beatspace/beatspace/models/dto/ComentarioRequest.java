package com.beatspace.beatspace.models.dto;

public record ComentarioRequest (
        String texto,
        String autor,
        String userimg,
        String username,
        String data,
        Long resenhaId


){
}
