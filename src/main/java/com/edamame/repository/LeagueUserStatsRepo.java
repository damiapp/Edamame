package com.edamame.repository;

import com.edamame.model.League;
import com.edamame.model.LeagueUserStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueUserStatsRepo extends JpaRepository<LeagueUserStats, Long> {
}
