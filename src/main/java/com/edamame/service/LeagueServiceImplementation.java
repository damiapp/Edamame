package com.edamame.service;

import com.edamame.model.League;
import com.edamame.model.User;
import com.edamame.repository.LeagueRepo;
import com.edamame.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class LeagueServiceImplementation implements LeagueService{
    private LeagueRepo leagueRepo;
    private UserRepo userRepo;

    public LeagueServiceImplementation(LeagueRepo leagueRepo,UserRepo userRepo)
    {
        this.userRepo = userRepo;
        this.leagueRepo = leagueRepo;
    }

    @Override
    public League createLeague(String name) {
        League league = new League();
        league.setName(name);
        league.setStartDate(new Date());
        String invCode = createRandomCode(6);
        league.setInviteCode(invCode);
        leagueRepo.save(league);
        addUserToLeague(invCode);
        return league;
    }

    @Override
    public boolean addUserToLeague(String invCode) {
        League league = leagueRepo.findByInviteCode(invCode);
        if(league==null)
            return false;

        //DATABASE COSTLY!! HAVE TO CHANGE TO COOKIES
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(auth.getName());
        List<User> users = league.getUsers();
        if(users!=null){
            int b = users.indexOf(user);
            if(b!=-1)
                return false;
            league.getUsers().add(user);
        }
        else{
            List<User> users1 = new ArrayList<>();
            users1.add(user);
            league.setUsers(users1);
        }
        user.getLeagues().add(league);
        leagueRepo.save(league);
        userRepo.save(user);
        return true;
    }

    @Override
    public League getLeagueByInvCode(String invCode) {
        League league = leagueRepo.findByInviteCode(invCode);
        if(league!=null)
            return league;
        return null;
    }


    public String createRandomCode(int codeLength){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output ;
    }
}
