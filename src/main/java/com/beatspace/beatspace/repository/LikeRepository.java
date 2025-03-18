package com.beatspace.beatspace.repository;

import com.beatspace.beatspace.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("SELECT l from Like l where l.userId = :userId and l.resenha.id= :resenhaId")
    Like findByResenhaAndUser(@Param("resenhaId") Long resenhaId , @Param("userId") String userId);
}
