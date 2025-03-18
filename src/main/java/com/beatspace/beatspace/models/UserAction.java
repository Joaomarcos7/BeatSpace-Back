package com.beatspace.beatspace.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Entity
@Table(name= "user_actions")
public class UserAction {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String action;
    @Getter
    @Setter
    private String details;

    @Getter
    @Setter
    private LocalDateTime timestamp;


    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

}
