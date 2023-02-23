package com.edamame.repository;

import com.edamame.model.League;
import com.edamame.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result, Long> {
}
