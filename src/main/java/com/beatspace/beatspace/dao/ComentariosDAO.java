package com.beatspace.beatspace.dao;

import com.beatspace.beatspace.models.Comentarios.Comentario;

import java.util.ArrayList;
import java.util.List;

public class ComentariosDAO {
    private List<Comentario> comentarios = new ArrayList<>();

    public ComentariosDAO() {

    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

}
