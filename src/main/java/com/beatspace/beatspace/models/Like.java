package com.beatspace.beatspace.models;

import com.beatspace.beatspace.models.Comentarios.Comentario;
import com.beatspace.beatspace.models.Comentarios.Resenha;
import com.beatspace.beatspace.models.dto.LikeResponse;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "resenha_id", nullable = false) // Define a chave estrangeira no banco
    private Resenha resenha;

    @Column(name = "userId")
    private String userId;

    public Like(){

    }
    public Like(Resenha resenha, String userId){
        resenha = resenha;
        userId = userId;
    }

    public LikeResponse toLikeResponse(){
        return new LikeResponse(
                this.id,
                this.userId
                );
    }

    // Getters e Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resenha getResenha() {
        return resenha;
    }

    public void setResenha(Resenha resenha) {
        this.resenha = resenha;
    }
}
