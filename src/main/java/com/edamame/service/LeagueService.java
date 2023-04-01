package com.edamame.service;

import com.edamame.model.League;
import com.edamame.model.User;

public interface LeagueService {
    League createLeague(String name);
    boolean addUserToLeague(String invCode);
    League getLeagueByInvCode(String invCode);
}
