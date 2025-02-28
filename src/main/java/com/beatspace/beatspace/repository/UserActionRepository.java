package com.beatspace.beatspace.repository;
import com.beatspace.beatspace.models.UserAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    List<UserAction> findByuserIdOrderByTimestampDesc(String userId);
}
