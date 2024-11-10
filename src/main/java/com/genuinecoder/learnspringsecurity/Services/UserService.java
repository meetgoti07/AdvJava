package com.genuinecoder.learnspringsecurity.Services;

import com.genuinecoder.learnspringsecurity.Repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.genuinecoder.learnspringsecurity.model.MyUser;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MyUserRepository userRepository;

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Fetch the username
        MyUser user = userRepository.findByUsername(username);
        return user.getId();
    }


    public MyUser findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(MyUser user) {
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}