package com.beatspace.beatspace.models.dto;

import java.util.List;

public record ResenhaResponse(
        Long id,
        String texto,
        String autor,
        String email,
        String username,
        String userimg,
        double nota,
        String data,
        String parentId,
        List<ComentarioResponse> comentarios, // Número total de comentários
        int totalLikes, // Número total de likes

        boolean liked //Já curtido ?
) {
}
