package com.beatspace.beatspace.repository;



import com.beatspace.beatspace.models.Comentarios.Comentario;
import com.beatspace.beatspace.models.Comentarios.Resenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenhaRepository extends JpaRepository<Resenha, Long> {
    List<Resenha> findByParentId(String id);

    List<Resenha> findByAutor(String id);

    @Query("SELECT Count(c.likes) FROM Resenha c WHERE c.id = :id" )
    int getTotalLikesFromResenha(Long id);

    @Query("SELECT c.comentarios FROM Resenha c WHERE c.id = :id" )
    List<Comentario> getCommentsFromResenha(Long id);

    @Query("SELECT AVG(c.nota) FROM Resenha  c WHERE c.parentId = :parentId")
    float getAverageGrade(@Param("parentId") String parentId);

    @Query("SELECT  new com.beatspace.beatspace.models.Comentarios.Resenha(c.parentId, AVG(c.nota)) FROM Resenha c GROUP BY c.parentId ORDER BY AVG(c.nota) DESC LIMIT 10")
    List<Resenha> getBestGrades();

    @Query("SELECT  new com.beatspace.beatspace.models.Comentarios.Resenha(c.parentId, AVG(c.nota)) FROM Resenha c WHERE c.genre = :genre GROUP BY c.parentId ORDER BY AVG(c.nota) DESC  LIMIT 10")
    List<Resenha> getBestGradesByGenre(@Param("genre") String genre);

    @Query("Select r from Resenha r where size(r.likes) > 0 order by size(r.likes) DESC LIMIT 10")
    List<Resenha> getResenhaMaisCurtidas();

    @Query("Select distinct r.genre from Resenha r")
    List<String> getGenres();
}
