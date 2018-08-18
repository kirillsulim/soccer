package io.su0.test.soccer;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.Team;

import java.util.HashMap;
import java.util.Map;

public final class TestUtil {

    private TestUtil() {
    }

    public static Group getGroupForTeams(String... teamNames) {
        Map<String, Team> teams = new HashMap<>();
        for (String teamName : teamNames) {
            Team team = new Team();
            team.setName(teamName);
            teams.put(teamName, team);
        }

        Group group = new Group();
        group.setTeams(teams);

        return group;
    }
}
