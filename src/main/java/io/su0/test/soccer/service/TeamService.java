package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.exceptions.NotFoundException;
import io.su0.test.soccer.util.functional.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TeamService {

    private final GroupService groupService;

    public TeamService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Result<Collection<Team>, RuntimeException> getTeams(String groupId) {
        return groupService.findGroupById(groupId).map(group -> group.getTeams().values());
    }

    @Transactional
    public Result<Team, RuntimeException> createTeam(String groupId, Team team) {
        return groupService.updateGroup(groupId, group -> {
            group.getTeams().put(team.getName(), team);
            return group;
        }).map(group -> group.getTeams().get(team.getName()));
    }

    public Result<Team, RuntimeException> getTeam(String groupId, String teamName) {
        return groupService.findGroupById(groupId).flatMap(group ->
                Result.fromOptional(
                        Optional.ofNullable(group.getTeams().get(teamName)),
                        () -> getTeamNotFoundException(teamName)
                )
        );
    }

    @Transactional
    public Result<Void, RuntimeException> deleteTeam(String groupId, String teamName) {
        return groupService.updateGroup(groupId, group -> {
            group.getTeams().remove(teamName);
            return group;
        }).map(group -> null);
    }

    @Transactional
    public Result<Team, RuntimeException> updateTeam(String groupId, String teamName, Function<Team, Team> updater) {
        return getTeam(groupId, teamName).flatMap(team -> groupService.updateGroup(groupId, group -> {
                    group.getTeams().put(teamName, updater.apply(group.getTeams().get(teamName)));
                    return group;
                })
                .map(group -> group.getTeams().get(teamName))
        );
    }

    private static NotFoundException getTeamNotFoundException(String teamName) {
        return new NotFoundException(String.format("Team with name '%s'", teamName));
    }
}
