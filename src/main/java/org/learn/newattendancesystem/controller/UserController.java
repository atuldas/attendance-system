package org.learn.newattendancesystem.controller;

import org.learn.newattendancesystem.entity.User;
import org.learn.newattendancesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/updateUser/{username}")
    public String showUpdateForm(@PathVariable String username, Model model) {
        User user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam String username, @RequestParam String role, @RequestParam String password, RedirectAttributes redirectAttributes) {
        User user = new User(username, password, role);
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "User updated successfully.");
        return "redirect:allusers";
    }

    @GetMapping("/allusers")
    public String allUser(Model model) {
        model.addAttribute("users",userService.findAll());
        return "userList";
    }

    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        // Return the name of the Thymeleaf template
        return "createUser";
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String role) {
        // Here you would add the logic to create the user
        // For simplicity, let's just print the details to the console
        User user = new User(username, password, role);
        userService.createUser(user);

        // Redirect back to the form after submission
        return "redirect:allusers";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(username);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "User deletion failed.");
        }
        return "redirect:/welcome";
    }


}
