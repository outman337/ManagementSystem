package com.backend.management.service;


import com.backend.management.exception.UserNotExistException;
import com.backend.management.model.Token;
import com.backend.management.model.User;
import com.backend.management.model.UserRole;
import com.backend.management.repository.UserRepository;
import com.backend.management.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class AuthenticationService {

    //这里需要authentication的管理器，我们之前变成bean就是为了在这里使用，然后jwtUtil是用了加密解密的
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public Token authenticate(User user, UserRole role) throws UserNotExistException{
        Authentication auth = null;
        try{
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        }
        catch(AuthenticationException exception){
            throw new UserNotExistException("User DNE");
        }
        if (auth == null || !auth.isAuthenticated() || !auth.getAuthorities().contains(new SimpleGrantedAuthority(role.name()))) {
            throw new UserNotExistException("User DNE");
        }
        return new Token(jwtUtil.generateToken(user.getUsername()));

    }

    public User getUser(String username) {
        return userRepository.findById(username).orElse(null);
    }
}
