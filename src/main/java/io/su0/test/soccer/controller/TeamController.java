package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.persistence.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @GetMapping("{id}")
    public Team getTeam(@PathVariable String id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @DeleteMapping("{id}")
    public void deleteTeam(@PathVariable String id) {
        teamRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Team updateTeam(@PathVariable String id, @RequestBody Team team) {
        teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        team.setId(id);
        return teamRepository.save(team);
    }
}
