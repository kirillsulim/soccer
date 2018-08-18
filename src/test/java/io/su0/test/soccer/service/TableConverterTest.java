package io.su0.test.soccer.service;

import io.su0.test.soccer.TestUtil;
import io.su0.test.soccer.domain.Game;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.domain.table.Table;
import io.su0.test.soccer.domain.table.TableRow;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

public class TableConverterTest {

    @Test
    public void shouldCalculateTableForSimpleGame() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil");
        group.setGames(Arrays.asList(
                new Game(){{
                    setTeam1("Argentina");
                    setTeam2("Brazil");
                    setTeam1Scores(1);
                    setTeam2Scores(2);
                }}
        ));

        Table table = TableConverter.convert(group);

        assertThat(table.getRows().get(1).getHeaders(), hasItems("Brazil", "1", "0", "0", "2/1", "3"));
        assertThat(table.getRows().get(2).getHeaders(), hasItems("Argentina", "0", "0", "1", "1/2", "0"));
    }

    @Test
    public void shouldCalculateTableForGameWithDraw() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil", "Croatia");
        group.setGames(Arrays.asList(
                new Game(){{
                    setTeam1("Argentina");
                    setTeam2("Brazil");
                    setTeam1Scores(2);
                    setTeam2Scores(2);
                }},
                new Game(){{
                    setTeam1("Brazil");
                    setTeam2("Croatia");
                    setTeam1Scores(3);
                    setTeam2Scores(0);
                }},
                new Game(){{
                    setTeam1("Croatia");
                    setTeam2("Argentina");
                    setTeam1Scores(1);
                    setTeam2Scores(2);
                }}
        ));

        Table table = TableConverter.convert(group);

        assertThat(table.getRows().get(1).getHeaders(), hasItems("Brazil", "1", "1", "0", "5/2", "4"));
        assertThat(table.getRows().get(2).getHeaders(), hasItems("Argentina", "1", "1", "0", "4/3", "4"));
        assertThat(table.getRows().get(3).getHeaders(), hasItems("Croatia", "0", "0", "2", "1/5", "0"));
    }
}
