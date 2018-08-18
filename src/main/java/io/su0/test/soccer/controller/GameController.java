package io.su0.test.soccer.controller;

import io.su0.test.soccer.domain.Game;
import io.su0.test.soccer.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames(@PathVariable String groupId) {
        return gameService.getGames(groupId).getOrThrow();
    }

    @PostMapping
    public Game createGame(@PathVariable String groupId, @RequestBody Game game) {
        return gameService.createGame(groupId, game).getOrThrow();
    }

    @GetMapping("{gameIdx}")
    public Game getGame(@PathVariable String groupId, @PathVariable int gameIdx) {
        return gameService.getGame(groupId, gameIdx).getOrThrow();
    }

    @DeleteMapping("{gameIdx}")
    public void deleteGame(@PathVariable String groupId, @PathVariable int gameIdx) {
        gameService.deleteGame(groupId, gameIdx).getOrThrow();
    }

    @PutMapping("{gameIdx}")
    public Game updateGame(@PathVariable String groupId, @PathVariable int gameIdx, @RequestBody Game game) {
        return gameService.updateGame(groupId, gameIdx, original -> game).getOrThrow();
    }
}
