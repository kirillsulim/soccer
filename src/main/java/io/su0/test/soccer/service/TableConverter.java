package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Game;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.table.Table;
import io.su0.test.soccer.domain.table.TableRow;
import io.su0.test.soccer.domain.table.TeamScore;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TableConverter {

    public static Table convert(Group group) {
        Table table = createTable();
        for (TeamScore score : getScore(group)) {
            table.getRows().add(convertToRow(score));
        }

        return table;
    }

    private static Table createTable() {
        TableRow headerRow = new TableRow();
        headerRow.setHeaders(Arrays.asList(
                "Team",
                "Win",
                "Draw",
                "Loose",
                "Scored/Missed",
                "Points"
        ));

        Table table = new Table();
        table.getRows().add(headerRow);

        return table;
    }

    private static List<TeamScore> getScore(Group group) {
        Map<String, TeamScore> scoreMap = group.getTeams().keySet().stream()
                .collect(Collectors.toMap(Function.identity(), TeamScore::new));

        for (Game game : group.getGames()) {
            TeamScore team1Score = scoreMap.get(game.getTeam1());
            TeamScore team2Score = scoreMap.get(game.getTeam2());

            updateScore(team1Score, game.getTeam1Scores(), game.getTeam2Scores());
            updateScore(team2Score, game.getTeam2Scores(), game.getTeam1Scores());
        }
        return scoreMap.values().stream()
                .sorted(Comparator
                        .comparingInt(TeamScore::getPoints)
                        .thenComparingInt(TeamScore::getScored)
                        .reversed()
                )
                .collect(Collectors.toList());
    }

    private static TableRow convertToRow(TeamScore teamScore) {
        TableRow row = new TableRow();

        row.getHeaders().add(teamScore.getTeamName());
        row.getHeaders().add(String.valueOf(teamScore.getWinCount()));
        row.getHeaders().add(String.valueOf(teamScore.getDrawCount()));
        row.getHeaders().add(String.valueOf(teamScore.getLooseCount()));
        row.getHeaders().add(String.format("%d/%d", teamScore.getScored(), teamScore.getMissed()));
        row.getHeaders().add(String.valueOf(teamScore.getPoints()));

        return row;
    }

    private static void updateScore(TeamScore score, int scored, int missed) {
        score.setScored(score.getScored() + scored);
        score.setMissed(score.getMissed() + missed);
        if (missed < scored) {
            score.setWinCount(score.getWinCount() + 1);
        }
        else if (missed == scored) {
            score.setDrawCount(score.getDrawCount() + 1);
        }
        else {
            score.setLooseCount(score.getLooseCount() + 1);
        }
    }
}
