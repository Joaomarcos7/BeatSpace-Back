package com.beatspace.beatspace.models.Comentarios;

import com.beatspace.beatspace.models.dto.ComentarioResponse;
import jakarta.persistence.*;

@Entity
@Table(name="comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userimg;

    @Column(nullable = false)
    private String data;

    @ManyToOne
    @JoinColumn(name = "resenha_id", nullable = false) // Define a chave estrangeira no banco
    private Resenha resenha;

    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Resenha getResenha() {
        return resenha;
    }

    public void setResenha(Resenha resenha) {
        this.resenha = resenha;
    }

    public ComentarioResponse toComentarioResponse(){
        return new ComentarioResponse(this.id,this.texto,this.userimg,this.username,this.autor,this.data);
    }

}
