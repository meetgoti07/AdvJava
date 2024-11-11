package com.genuinecoder.learnspringsecurity.Controllers;

import com.genuinecoder.learnspringsecurity.Services.UserService;
import com.genuinecoder.learnspringsecurity.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showUserProfilePage(Model model) {
        Long userId = userService.getCurrentUserId();
        MyUser user = userService.findById(userId);

        model.addAttribute("user", user);
        return "user-profile";  // This is the form page for the user to edit their profile
    }

    @PostMapping("/update-profile")  // Use POST for form submission, not PUT
    public String updateUserProfile(@ModelAttribute("user") MyUser user, Model model) {
        // Assuming you have a method in your service to update the user
        userService.updateUserProfile(user);

        // Optionally, you can add a success message to the model or redirect to another page
        model.addAttribute("message", "Profile updated successfully!");
        return "redirect:/login";  // Redirect to the profile page after update
    }
}


