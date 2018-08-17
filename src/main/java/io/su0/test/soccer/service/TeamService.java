package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.util.functional.Result;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TeamService {

    private final GroupService groupService;

    public TeamService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Result<Collection<Team>, RuntimeException> getTeams(String groupId) {
        return groupService.findGroupById(groupId).map(group -> group.getTeams().values());
    }

    public Result<Team, RuntimeException> createTeam(String groupId, Team team) {
        return groupService.updateGroup(groupId, group -> {
            group.getTeams().put(team.getName(), team);
            return group;
        }).map(group -> group.getTeams().get(team.getName()));
    }
}
