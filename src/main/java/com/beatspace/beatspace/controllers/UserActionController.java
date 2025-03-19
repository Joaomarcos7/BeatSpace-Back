package com.beatspace.beatspace.controllers;
import com.beatspace.beatspace.models.UserAction;
import com.beatspace.beatspace.services.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/historico")
public class UserActionController {


    @Autowired
    private UserActionService userActionService;

    @PostMapping
    public void saveAction(@RequestBody UserAction userAction) {
        userActionService.saveUserAction(userAction.getUserId(), userAction.getUsername(), userAction.getAction(), userAction.getDetails());
    }

    @GetMapping("/{userId}")
    public List<UserAction> getUserHistory(@PathVariable String userId) {
        return userActionService.getUserActions(userId);
    }

    @Transactional
    @DeleteMapping("/{userId}/{actionId}")
    public void deleteUserAction(@PathVariable String userId, @PathVariable Long actionId) {
        userActionService.deleteUserAction(userId, actionId);
        System.out.println("User action deleted");
    }


}
