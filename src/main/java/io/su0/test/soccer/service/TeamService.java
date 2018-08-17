package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.Team;

import java.util.List;
import java.util.Optional;

public class TeamService {

    private final GroupService groupService;

    public TeamService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Optional<List<Team>> getTeamsForGroup(String groupId) {
        return groupService.findGroupById(groupId).map(Group::getTeams);
    }
}
