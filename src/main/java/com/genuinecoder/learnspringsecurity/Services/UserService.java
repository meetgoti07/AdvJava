package com.genuinecoder.learnspringsecurity.Services;

import com.genuinecoder.learnspringsecurity.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.genuinecoder.learnspringsecurity.model.MyUser;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<MyUser> findAll() {
        return userRepository.findAll();
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