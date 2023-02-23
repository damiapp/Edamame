package com.edamame.repository;

import com.edamame.model.League;
import com.edamame.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepo extends JpaRepository<Match, Long> {
}
