package com.backend.management.controller;

import com.backend.management.model.User;
import com.backend.management.model.UserRole;
import com.backend.management.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@RestController
public class MoveController {

    private final MoveService moveService;

    @Autowired
    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @PutMapping(value = "/moveIn")
    public void moveIn(String username, String apartmentNumber) {
        moveService.moveIn(username, apartmentNumber);
    }

    @PutMapping(value = "/moveInAndAssignNewOwner")
    public void moveInAndAssignNewOwner(String username, String apartmentNumber, String newOwnerUsername) {
        moveService.moveInAndAssignNewOwner(username, apartmentNumber, newOwnerUsername);
    }

    @PutMapping(value = "/moveOut")
    public void moveOut(String username) {
        moveService.moveOut(username);
    }

    @PutMapping(value = "/moveOutAndAssignNewOwner")
    public void moveOutAndAssignNewOwner(String username, String newOwnerUsername) {
        moveService.moveOutAndAssignNewOwner(username, newOwnerUsername);
    }

    @GetMapping("getFlatmates")
    public Set<String> getFlatmates(String username) {
        return moveService.getFlatmates(username);
    }
}
