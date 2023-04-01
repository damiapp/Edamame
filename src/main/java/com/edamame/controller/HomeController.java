package com.edamame.controller;

import com.edamame.model.Hero;
import com.edamame.model.League;
import com.edamame.model.Match;
import com.edamame.model.User;
import com.edamame.service.HeroService;
import com.edamame.service.LeagueService;
import com.edamame.service.MatchService;
import com.edamame.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    private UserService userService;
    private LeagueService leagueService;

    private MatchService matchService;

    private HeroService heroService;

    @Autowired
    public HomeController(UserService userService, LeagueService leagueService,
                          MatchService matchService, HeroService heroService) {
        this.userService = userService;
        this.leagueService = leagueService;
        this.matchService = matchService;
        this.heroService = heroService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            User user = new User();
            model.addAttribute("user", user);
            return "registration";
        }
        return "redirect:/";
    }

    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") @NotNull User user,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(user.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "/registration";
        }

        userService.saveUser(user);
        return "redirect:/registration?success";
    }
    @GetMapping("/createleague")
    public String createLeagueForm(){
        return "createleague";
    }
    @GetMapping("/joinleague")
    public String joinLeagueForm(){
        return "joinleague";
    }
    @GetMapping("/yourleagues")
    public String leagueList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userService.findUserByEmail(auth.getName());
        List<League> leagues = user.getLeagues();
        model.addAttribute("leagues",leagues);
        return "yourleagues";
    }

    @GetMapping("/league")
    public String leagueWithInvCode(Model model, @RequestParam String invCode){
        League league = leagueService.getLeagueByInvCode(invCode);
        model.addAttribute("league",league);
        return "league";
    }
    @PostMapping("/createleague/save")
    public String createLeague(@RequestParam("name") String name,
                               Model model){
        League league = leagueService.createLeague(name);
        model.addAttribute("league",league);
        //maybe send it to list of leagues????

        return "redirect:/league?invCode="+league.getInviteCode();
    }

    @PostMapping("/addusertoleague")
    public String joinLeague(@RequestParam("invCode") String invCode,
                               Model model){
        if(leagueService.addUserToLeague(invCode))
            return "redirect:/league?invCode="+invCode;
        else
            return "redirect:/joinleague?error";
    }

    @PostMapping("/match")
    public String createMatch(@RequestParam("invCode") long idMatch,
                              Model model){
        ;
        return "match";
    }

    @PostMapping("/match/save")
    public String saveMatch(Model model){
        return "redirect:/match";
    }

    @GetMapping("/crmatch")
    public String createMatchForm(@RequestParam("invCode") String invCode,
                             Model model){
        League league = leagueService.getLeagueByInvCode(invCode);
        List<Hero> heroes = heroService.findAllHeroes();
        model.addAttribute("league",league);
        model.addAttribute("heroes",heroes);
        return "creatematch";
    }

}
