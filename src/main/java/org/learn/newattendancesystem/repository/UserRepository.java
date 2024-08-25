package org.learn.newattendancesystem.repository;

import org.learn.newattendancesystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    void delete(User user);
}