package com.bstack.bstackapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bstack.bstackapp.models.Team;
import com.bstack.bstackapp.models.UserAccount;
import com.bstack.bstackapp.repositories.TeamRepository;
import com.bstack.bstackapp.repositories.UserRepository;

@RestController
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/team/create")
    public ResponseEntity<String> createTeam(
                                             @AuthenticationPrincipal UserDetails userDetails,
                                             @RequestBody Team team) {

        Optional<UserAccount> optionalUser = userRepo.findUserByUsername(userDetails.getUsername());
        logger.info("This is the team: {}", team);
        if(optionalUser.isPresent()) {
            UserAccount user = optionalUser.get();

            Team newTeam = new Team();
            newTeam.setOwner(user);
            newTeam.setTeamName(team.getTeamName());
            newTeam.setTeamDetails(team.getTeamDetails());

            user.addTeam(newTeam);
            userRepo.save(user);

            return ResponseEntity.ok().body("Team created");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Team already exists");
        }
    }

    @GetMapping("/public/teams")
    public List<Team> getTeams() {
        Iterable<Team> teamsIterable = teamRepo.findAll();
        List<Team> teamsList = new ArrayList<>();
        teamsIterable.forEach(teamsList::add);
        return teamsList;
    }
}
