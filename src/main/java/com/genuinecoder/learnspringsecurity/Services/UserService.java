//package com.genuinecoder.learnspringsecurity.Services;
//
//import com.genuinecoder.learnspringsecurity.Repositories.MyUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import com.genuinecoder.learnspringsecurity.model.MyUser;
//
//import java.util.List;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private MyUserRepository userRepository;
//
//    public List<MyUser> findAll() {
//        return userRepository.findAll();
//    }
//
//    public Long getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName(); // Fetch the username
//        MyUser user = userRepository.findByUsername(username);
//        return user.getId();
//    }
//    public void updateUserProfile(MyUser user) {
//
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName(); // Fetch the username
//        MyUser existingUser = userRepository.findByUsername(username);
//        //user exist check
//        if (existingUser == null) {
//            throw new RuntimeException("User not found");
//        }
//
//
//
//        // Update the existing user fields
//
//        existingUser.setUsername(user.getUsername());
//
//
//
//        existingUser.setPassword(user.getPassword());
//
//
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPhone(user.getPhone());
//        existingUser.setAddress(user.getAddress());
//        existingUser.setRole(user.getRole());
//
//
//        // Save the updated user
//        userRepository.save(existingUser);
//    }
//
//
//
//    public MyUser findById(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    public void save(MyUser user) {
//        userRepository.save(user);
//    }
//
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
//}


package com.genuinecoder.learnspringsecurity.Services;

import com.genuinecoder.learnspringsecurity.Repositories.MyUserRepository;
import com.genuinecoder.learnspringsecurity.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Add BCryptPasswordEncoder

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Fetch the username
        MyUser user = userRepository.findByUsername(username);
        return user.getId();
    }

    public void updateUserProfile(MyUser user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Fetch the username
        MyUser existingUser = userRepository.findByUsername(username);

        // Check if the user exists
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }

        // Update the existing user fields
        existingUser.setUsername(user.getUsername());

        // Encode the password before setting it
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        existingUser.setRole(user.getRole());

        // Save the updated user
        userRepository.save(existingUser);
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
