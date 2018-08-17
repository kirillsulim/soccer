package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.exceptions.NotFoundException;
import io.su0.test.soccer.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/groups/{groupId}/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getGroups(@PathVariable String groupId) {
        return teamService.getTeamsForGroup(groupId).orElseThrow(NotFoundException::new);
    }
/*
    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @GetMapping("{id}")
    public Group getGroup(@PathVariable String id) {
        return groupService.findGroupById(id).orElseThrow(NotFoundException::new);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.deleteById(id);
    }

    @PutMapping("{id}")
    public Group updateGroup(@PathVariable String id, @RequestBody Group group) {
        return groupService.updateGroup(id, group).orElseThrow(NotFoundException::new);
    }*/
}
