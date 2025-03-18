package com.beatspace.beatspace.repository;

import com.beatspace.beatspace.models.Comentarios.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

}
