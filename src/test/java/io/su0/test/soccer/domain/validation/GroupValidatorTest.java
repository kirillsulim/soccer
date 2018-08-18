package io.su0.test.soccer.domain.validation;

import io.su0.test.soccer.TestUtil;
import io.su0.test.soccer.domain.Game;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.util.functional.Result;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GroupValidatorTest {

    @Test
    public void shouldValidateGroupWith4Teams() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil", "Croatia", "Dominicana");

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(true));
    }

    @Test
    public void shouldNotValidateGroupWith5Teams() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil", "Croatia", "Dominicana", "England");

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(false));
        assertThat(validate.getError().getMessage(), is("Illegal team count 5"));
    }

    @Test
    public void shouldValidateGroupWithCorrectGames() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil");

        group.setGames(Arrays.asList(
                new Game(){{
                    setTeam1("Argentina");
                    setTeam2("Brazil");
                }}
        ));

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(true));
    }

    @Test
    public void shouldNotValidateGroupWithGamesWithForeignTeams() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil");

        group.setGames(Arrays.asList(
                new Game(){{
                    setTeam1("Argentina");
                    setTeam2("England");
                }}
        ));

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(false));
        assertThat(validate.getError().getMessage(), is("Team England is not in group"));
    }

    @Test
    public void shouldNotValidateGroupWithDoubledMatches() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil");

        group.setGames(Arrays.asList(
                new Game(){{
                    setTeam1("Argentina");
                    setTeam2("Brazil");
                }},
                new Game(){{
                    setTeam1("Brazil");
                    setTeam2("Argentina");
                }}
        ));

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(false));
        assertThat(validate.getError().getMessage(), is("Match Brazil:Argentina already played"));
    }

    @Test
    public void shouldNotValidateGroupWithSelfPlayingMatch() {
        Group group = TestUtil.getGroupForTeams("Argentina", "Brazil");

        group.setGames(Arrays.asList(
                new Game(){{
                    setTeam1("Argentina");
                    setTeam2("Argentina");
                }}
        ));

        Result<Group, RuntimeException> validate = GroupValidator.validate(group);
        assertThat(validate.isOk(), is(false));
        assertThat(validate.getError().getMessage(), is("Self-played match cannot be played"));
    }

}
