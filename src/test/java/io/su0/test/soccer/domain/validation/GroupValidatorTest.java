package io.su0.test.soccer.domain.validation;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.Team;
import io.su0.test.soccer.exceptions.ValidationException;
import io.su0.test.soccer.util.functional.Result;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GroupValidatorTest {

    @Test
    public void shouldValidateGroupWith4Teams() {
        Group group = getGroupForTeams("Argentina", "Brazil", "Chech", "Dominicana");

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(true));
    }

    @Test
    public void shouldNotValidateGroupWith5Teams() {
        Group group = getGroupForTeams("Argentina", "Brazil", "Chech", "Dominicana", "England");

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(false));
        assertThat(validate.getError().getMessage(), is("Illegal team count 5"));
    }

    private static Group getGroupForTeams(String ... teamNames) {
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
