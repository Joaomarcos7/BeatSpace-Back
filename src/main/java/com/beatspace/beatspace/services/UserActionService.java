package com.beatspace.beatspace.services;

import com.beatspace.beatspace.models.UserAction;
import com.beatspace.beatspace.repository.UserActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserActionService {
    @Autowired
    private UserActionRepository userActionRepository;

    public void saveUserAction(String userId, String username, String action, String details) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setUsername(username);
        userAction.setAction(action);
        userAction.setDetails(details);
        userActionRepository.save(userAction);
    }

    public List<UserAction> getUserActions(String userId) {
        return userActionRepository.findByuserIdOrderByTimestampDesc(userId);
    }
    @Transactional
    public void deleteUserAction(String userId, Long actionId) {
        userActionRepository.deleteByUserIdAndId(userId, actionId);
    }

    @Transactional
    public void deleteAllUserActions(String userId) {
        userActionRepository.deleteAllByUserId(userId);
    }

}
