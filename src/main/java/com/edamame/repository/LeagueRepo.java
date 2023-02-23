package com.edamame.repository;

import com.edamame.model.League;
import com.edamame.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepo extends JpaRepository<League, Long> {
}
