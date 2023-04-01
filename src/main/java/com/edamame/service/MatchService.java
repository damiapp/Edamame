package com.edamame.service;

import com.edamame.model.League;
import com.edamame.model.Match;
import com.edamame.repository.MatchRepo;

public interface MatchService {
    Match createMatch(League league);
}
