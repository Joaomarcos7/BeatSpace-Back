package com.beatspace.beatspace.dao;

import com.beatspace.beatspace.models.Comentario;

import java.util.ArrayList;
import java.util.List;

public class ComentariosDAO {
    private List<Comentario> comentarios = new ArrayList<>();

    public ComentariosDAO() {
        this.comentarios.add(new Comentario("Muito bom essa música, gostei demais","João Marcos"));
        this.comentarios.add(new Comentario("Não gostei muito, achei chato","João Pedro"));
        this.comentarios.add(new Comentario("Top demais!","José"));
        this.comentarios.add(new Comentario("Melhor coisa do meu ano!", "Maria"));
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

}
