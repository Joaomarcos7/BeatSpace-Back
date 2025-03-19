package com.beatspace.beatspace.repository;
import com.beatspace.beatspace.models.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    List<UserAction> findByuserIdOrderByTimestampDesc(String userId);
    @Transactional
    void deleteByUserIdAndId(String userId, Long actionId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserAction ua WHERE ua.userId = :userId")
    void deleteAllByUserId(String userId);

}
