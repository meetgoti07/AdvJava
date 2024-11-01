package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.Services.UserService;
import com.genuinecoder.learnspringsecurity.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserManagementController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/manage-users")
    public String manageUsers(Model model) {
        List<MyUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "manage_users";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam String username, @RequestParam String role, @RequestParam(required = false) Long userId) {
        MyUser user = new MyUser();
        if (userId != null) {
            user = userService.findById(userId);
        }
        user.setUsername(username);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode("defaultPassword")); // Set a default password
        userService.save(user);
        return "redirect:/admin/manage-users";
    }


    @PostMapping("/edit-user")
    public String editUser(@RequestParam Long userId, @RequestParam String username, @RequestParam String role) {
        MyUser user = userService.findById(userId);
        if (user != null) {
            user.setUsername(username);
            if (role.startsWith("ROLE_")) {
                role = role.substring(5);
            }
            user.setRole(role);
            userService.save(user);
        }
        return "redirect:/admin/manage-users";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteById(userId);
        return "redirect:/admin/manage-users";
    }
}