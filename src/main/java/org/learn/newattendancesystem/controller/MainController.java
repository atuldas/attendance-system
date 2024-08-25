package org.learn.newattendancesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndex() {
        return "index";  // Renders index.html
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";  // Renders login.html
    }

    @GetMapping("/welcome")
    public String showWelcome() {
        return "welcome";  // Renders welcome.html
    }
}
