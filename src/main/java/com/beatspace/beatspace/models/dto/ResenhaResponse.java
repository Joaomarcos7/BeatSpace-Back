package com.beatspace.beatspace.models.dto;

import java.util.List;

public record ResenhaResponse(
        Long id,
        String texto,
        String autor,
        String username,
        String userimg,
        double nota,
        String data,
        String parentId,

        String genre,
        List<ComentarioResponse> comentarios, // Número total de comentários

        List<LikeResponse> likes
) {
}
