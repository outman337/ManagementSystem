package com.backend.management.controller;

import com.backend.management.model.User;
import com.backend.management.model.UserRole;
import com.backend.management.service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    //add tenant
    @PostMapping("/register/tenant")
    public void addTenant(@RequestBody User user){
        registerService.add(user, UserRole.ROLE_TENANT);
    }
    @PostMapping("/register/manager")
    public void addManger(@RequestBody User user){
        registerService.add(user, UserRole.ROLE_MANAGER);
    }
    @PostMapping("/register/provider")
    public void addProvider(@RequestBody User user){
        registerService.add(user, UserRole.ROLE_PROVIDER);
    }

}
