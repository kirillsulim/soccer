package io.su0.test.soccer.service;

import io.su0.test.soccer.domain.Game;
import io.su0.test.soccer.domain.Group;
import io.su0.test.soccer.exceptions.NotFoundException;
import io.su0.test.soccer.util.functional.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class GameService {

    private final GroupService groupService;

    public GameService(GroupService groupService) {
        this.groupService = groupService;
    }

    public Result<List<Game>, RuntimeException> getGames(String groupId) {
        return groupService.findGroupById(groupId).map(Group::getGames);
    }

    @Transactional
    public Result<Game, RuntimeException> createGame(String groupId, Game game) {
        return groupService.updateGroup(groupId, group -> {
            group.getGames().add(game);
            return group;
        }).flatMap(group -> Result.fromOptional(last(group.getGames()), RuntimeException::new));
    }

    public Result<Game, RuntimeException> getGame(String groupId, int gameIdx) {
        return groupService.findGroupById(groupId).flatMap(group ->
                Result.fromOptional(
                        getOptionalByIndex(group.getGames(), gameIdx),
                        () -> getGameNotFoundException(gameIdx)
                )
        );
    }

    @Transactional
    public Result<Void, RuntimeException> deleteGame(String groupId, int gameIdx) {
        return groupService.updateGroup(groupId, group -> {
            group.getGames().remove(gameIdx);
            return group;
        }).map(group -> null);
    }

    @Transactional
    public Result<Game, RuntimeException> updateGame(String groupId, int gameIdx, Function<Game, Game> updater) {
        return getGame(groupId, gameIdx).flatMap(team -> groupService.updateGroup(groupId, group -> {
                    group.getGames().set(gameIdx, updater.apply(group.getGames().get(gameIdx)));
                    return group;
                })
                .map(group -> group.getGames().get(gameIdx))
        );
    }

    private static NotFoundException getGameNotFoundException(int gameIdx) {
        return new NotFoundException(String.format("Game with index '%d'", gameIdx));
    }

    private static <T> Optional<T> last(List<T> list) {
        if (list.size() == 0) {
            return Optional.empty();
        }
        else {
            return Optional.of(list.get(list.size() - 1));
        }
    }

    private static <T> Optional<T> getOptionalByIndex(List<T> list, int idx) {
        if (idx < list.size()) {
            return Optional.ofNullable(list.get(idx));
        }
        else {
            return Optional.empty();
        }
    }
}
