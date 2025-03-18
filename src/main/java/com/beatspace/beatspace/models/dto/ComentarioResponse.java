package com.beatspace.beatspace.models.dto;

public record ComentarioResponse (
        Long id,
        String texto,

        String userimg,

        String username,

        String autor,

        String data
) {
}
