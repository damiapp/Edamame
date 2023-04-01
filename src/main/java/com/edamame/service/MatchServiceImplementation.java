package com.edamame.service;

import com.edamame.model.League;
import com.edamame.model.Match;
import com.edamame.repository.MatchRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MatchServiceImplementation implements MatchService{
    private MatchRepo matchRepo;

    public MatchServiceImplementation(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    @Override
    public Match createMatch(League league) {
        Match match = new Match();
        match.setStartDate(new Date());
        match.setLeague(league);
        matchRepo.save(match);
        return match;
    }
}
