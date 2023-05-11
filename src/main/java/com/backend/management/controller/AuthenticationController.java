package com.backend.management.controller;

import com.backend.management.model.Apartment;
import com.backend.management.model.Token;
import com.backend.management.model.User;
import com.backend.management.model.UserRole;
import com.backend.management.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public static class AuthenticationData {

        public final Token token;
        public final String apartmentNumber;

        public AuthenticationData(Token token, String apartmentNumber) {
            this.token = token;
            this.apartmentNumber = apartmentNumber;
        }
    }

    @PostMapping("/authenticate/tenant")
    public AuthenticationData authenticateTenant(@RequestBody User user) {
        Token token = authenticationService.authenticate(user, UserRole.ROLE_TENANT);
        Apartment apartment = authenticationService.getUser(user.getUsername()).getApartmentNumber();
        return new AuthenticationData(
                token,
                apartment != null ? apartment.getApartmentId() : null
        );
    }

    @PostMapping("/authenticate/manager")
    public Token authenticateManager(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_MANAGER);
    }

    @PostMapping("/authenticate/provider")
    public Token authenticateProvider(@RequestBody User user) {
        return authenticationService.authenticate(user, UserRole.ROLE_PROVIDER);
    }

}
