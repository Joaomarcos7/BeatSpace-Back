package com.beatspace.beatspace.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private String autor;

    // Construtor padrão
    public Comentario() {}

    public Comentario(String texto, String autor){
        this.texto = texto;
        this.autor = autor;
    }

    // Getters e Setters
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
