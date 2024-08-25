package org.learn.newattendancesystem;

import org.learn.newattendancesystem.entity.User;
import org.learn.newattendancesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("kanika");
            admin.setPassword(new BCryptPasswordEncoder().encode("kanika123"));
            admin.setRole("admin");
            userRepository.save(admin);
            System.out.println("Admin user created");
        }
    }
}

