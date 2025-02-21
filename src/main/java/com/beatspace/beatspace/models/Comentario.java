package com.beatspace.beatspace.models;

import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;


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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String userimg;

    @Column(nullable = false)
    private int nota;

    @Column(nullable = false)
    private String data;

    @Column(nullable=false)
    private String parentId;




    // Construtor padrão
    public Comentario() {}

    public Comentario(String texto, String autor,int nota,String data ,String parentId, String username, String userimg){
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

    // Getters e Setters

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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
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

    private void ValidarFaixaDaNota(int nota){
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
