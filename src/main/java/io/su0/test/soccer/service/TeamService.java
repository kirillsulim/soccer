package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.util.functional.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final GroupService groupService;

    public TeamService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Result<List<Team>, RuntimeException> getTeams(String groupId) {
        return groupService.findGroupById(groupId).map(Group::getTeams);
    }
}
