package com.backend.management.service;

import com.backend.management.exception.UserAlreadyExistException;
import com.backend.management.model.User;
import com.backend.management.model.Authority;
import com.backend.management.model.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend.management.repository.UserRepository;
import com.backend.management.repository.AuthorityRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, AuthorityRepository authorityRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void add(User user, UserRole role){
        if(userRepository.existsById(user.getUsername())){
            throw new UserAlreadyExistException("User exist already");
        }
        //加密用户的密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        //通过repository加user
        userRepository.save(user);
        //通过user的信息来获取的user是哪一类用户，tenant，manager还是provider
        authorityRepository.save(new Authority(user.getUsername(), role.name()));
    }
}
