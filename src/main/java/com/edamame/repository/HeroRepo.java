package com.edamame.repository;

import com.edamame.model.Hero;
import com.edamame.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepo extends JpaRepository<Hero, Long> {
}
