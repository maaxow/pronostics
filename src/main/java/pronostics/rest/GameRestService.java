package pronostics.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import pronostics.model.Game;
import pronostics.repository.GameRepository;
import pronostics.service.GameService;

@Component
@Path("/game")
public class GameRestService {

	@Inject
	private GameRepository gameRepository;

	@Inject
	private GameService gameService;

	@GET
	public Response findAll() {
		if (gameRepository != null) {
			List<Game> games = gameRepository.findAll();
			System.out.println(games.toString());
			gameService.printSomeStuff();
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response findByIdToto(@PathParam("id") String id) {
		if (gameRepository != null) {
			Game game = gameRepository.findById(Long.parseLong(id));
			System.out.println("[" + this.hashCode() + "] " + game.toString());
			gameService.printSomeStuff();
			return Response.ok(game).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
