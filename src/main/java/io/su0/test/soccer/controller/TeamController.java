package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/groups/{groupId}/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public Collection<Team> getGroups(@PathVariable String groupId) {
        return teamService.getTeams(groupId).getOrThrow();
    }

    @PostMapping
    public Team createTeam(@PathVariable String groupId, @RequestBody Team team) {
        return teamService.createTeam(groupId, team).getOrThrow();
    }

    @GetMapping("{id}")
    public Team getTeam(@PathVariable String groupId, @PathVariable String id) {
        return teamService.getTeam(groupId, id).getOrThrow();
    }

    @DeleteMapping("{id}")
    public void deleteTeam(@PathVariable String groupId, @PathVariable String id) {
        teamService.deleteTeam(groupId, id).getOrThrow();
    }

    @PutMapping("{id}")
    public Team updateTeam(@PathVariable String groupId, @PathVariable String id, @RequestBody Team newData) {
        return teamService.updateTeam(groupId, id, old -> newData).getOrThrow();
    }
}
