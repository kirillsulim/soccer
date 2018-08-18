package io.su0.test.soccer.domain.validation;

import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.exceptions.ValidationException;
import io.su0.test.soccer.util.functional.Result;

public class GroupValidator {

    private static final int MAX_TEAM_COUNT = 4;

    public static Result<Group, RuntimeException> validate(Group group) {
        return validateTeamCount(group);
    }

    private static Result<Group, RuntimeException> validateTeamCount(Group group) {
        int teamCount = group.getTeams().size();
        if (teamCount <= MAX_TEAM_COUNT) {
            return Result.ok(group);
        }
        else {
            return Result.error(new ValidationException(String.format("Illegal team count %d", teamCount)));
        }
    }
}
