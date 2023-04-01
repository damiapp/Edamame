package com.edamame.repository;

import com.edamame.model.League;
import com.edamame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
