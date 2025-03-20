package com.beatspace.beatspace.models.Comentarios;

import com.beatspace.beatspace.models.Like;
import com.beatspace.beatspace.models.dto.ResenhaResponse;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resenhas")
public class Resenha {

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
    private String genre;

    @Column(nullable = false)
    private double nota;

    @Column(nullable = false)
    private String data;

    @Column(nullable=false)
    private String parentId;

    @OneToMany(mappedBy = "resenha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "resenha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();




    // Construtor padrão
    public Resenha() {}

    public Resenha(String parentId,double nota){
        this.parentId=parentId;
        this.nota = nota;
    }

    public Resenha(String texto, String autor,int nota,String data ,String parentId, String username, String userimg){
        this.ValidarTamanhodoTexto(texto);
        this.ValidarFaixaDaNota(nota);
        this.ValidarFormatoData(data);
        this.texto = texto;
        this.autor = autor;
        this.nota = nota;
        this.data = data;
        this.parentId = parentId;
        this.userimg = userimg;
        this.username = username;
    }

    // Método para converter Resenha em ResenhaRecord
    public ResenhaResponse toResenhaResponse() {
        return new ResenhaResponse(
                this.id,
                this.texto,
                this.autor,
                this.username,
                this.userimg,
                this.nota,
                this.data,
                this.parentId,
                this.genre,
                this.comentarios.stream().map(x->x.toComentarioResponse()).toList(),
                this.likes.stream().map(x->x.toLikeResponse()).toList()
        );
    }

    // Getters e Setters


    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    // Getter para 'username'
    public String getUsername() {
        return username;
    }

    // Setter para 'username'
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter para 'userimg'
    public String getUserimg() {
        return userimg;
    }

    // Setter para 'userimg'
    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.ValidarTamanhodoTexto(texto);
        this.texto = texto;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.ValidarFaixaDaNota(nota);
        this.nota = nota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.ValidarFormatoData(data);
        this.data = data;
    }

    private void  ValidarTamanhodoTexto(String texto){
        if(texto.length() > 250){
            throw new RuntimeException("Texto não pode ser maior que 250 caracteres");
        }
    }

    private void ValidarFaixaDaNota(double nota){
        if(nota > 10 || nota < 0 ){
            throw new RuntimeException("A nota tem que estar entre a faixa de 0 a 10");
        }
    }

    private void ValidarFormatoData(String data){
        try {
            Instant instant = Instant.parse(data);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Data no formato que não é valido!");
        }
    }


}
