package com.edamame.service;

import com.edamame.model.Hero;
import com.edamame.repository.HeroRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImplementation implements HeroService{
    private HeroRepo heroRepo;

    public HeroServiceImplementation(HeroRepo heroRepo) {
        this.heroRepo = heroRepo;
    }

    @Override
    public List<Hero> findAllHeroes() {
        return heroRepo.findAll();
    }
}
