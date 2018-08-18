package io.su0.test.soccer.domain.validation;

import io.su0.test.soccer.domain.Game;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.exceptions.ValidationException;
import io.su0.test.soccer.util.functional.Result;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GroupValidator {

    private static class Match {
        private final String team1Name;
        private final String team2Name;

        public Match(String team1Name, String team2Name) {
            this.team1Name = team1Name;
            this.team2Name = team2Name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Match match = (Match) o;
            return (Objects.equals(team1Name, match.team1Name) && Objects.equals(team2Name, match.team2Name))
                    || (Objects.equals(team1Name, match.team2Name) && Objects.equals(team2Name, match.team1Name));
        }

        @Override
        public int hashCode() {
            /* Here we use some sophisticated hash function. Because we need hash(t1, t2) == hash(t2, t1) we can
             * XOR hashes from t1 and t2 to get this behavior.
             */
            return team1Name.hashCode() ^ team2Name.hashCode();
        }
    }

    private static final int MAX_TEAM_COUNT = 4;

    public static Result<Group, RuntimeException> validate(Group group) {
        return validateTeamCount(group)
                .flatMap(GroupValidator::validateGames);
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

    private static Result<Group, RuntimeException> validateGames(Group group) {
        Set<Match> matches = new HashSet<>();

        for (Game game : group.getGames()) {
            Result<Group, RuntimeException> validation = validateTeamsInGroup(group, game);
            if (!validation.isOk()) {
                return validation;
            }
            validation = validateMatchIsNotSelfPlayed(group, game);
            if (!validation.isOk()) {
                return validation;
            }

            Match match = new Match(game.getTeam1(), game.getTeam2());
            if (matches.contains(match)) {
                return Result.error(new ValidationException(String.format("Match %s:%s already played", game.getTeam1(), game.getTeam2())));
            } else {
                matches.add(match);
            }
        }

        return Result.ok(group);
    }

    private static Result<Group, RuntimeException> validateTeamsInGroup(Group group, Game game) {
        if (!group.getTeams().keySet().contains(game.getTeam1())) {
            return Result.error(new ValidationException(String.format("Team %s is not in group", game.getTeam1())));
        }
        if (!group.getTeams().keySet().contains(game.getTeam2())) {
            return Result.error(new ValidationException(String.format("Team %s is not in group", game.getTeam2())));
        }
        return Result.ok(group);
    }

    private static Result<Group, RuntimeException> validateMatchIsNotSelfPlayed(Group group, Game game) {
        if (game.getTeam1().equals(game.getTeam2())) {
            return Result.error(new ValidationException("Self-played match cannot be played"));
        }
        else {
            return Result.ok(group);
        }
    }
}
