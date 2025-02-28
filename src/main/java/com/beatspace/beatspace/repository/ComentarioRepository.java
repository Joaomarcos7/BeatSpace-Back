package com.beatspace.beatspace.repository;



import com.beatspace.beatspace.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    public List<Comentario> findByParentId(String id);

}
