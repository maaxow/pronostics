package pronostics.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import pronostics.model.Game;
import pronostics.model.Team;
import pronostics.repository.GameRepository;
import pronostics.repository.TeamRepository;
import pronostics.service.GameService;

@Component
@Path("/game")
public class GameRestService {

	@Inject
	private GameRepository gameRepository;
	
	@Inject
	private TeamRepository teamRepository;

	@Inject
	private GameService gameService;

	@GET
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAll() {
		// System.out.println("gameRepo : " + gameRepository);
		// System.out.println("gameService : " + gameService);
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
	@Path("/team")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllTeam() {
		// System.out.println("gameRepo : " + gameRepository);
		// System.out.println("gameService : " + gameService);
		if (gameRepository != null) {
			List<Team> games = teamRepository.findAll();
			System.out.println(games.toString());
			gameService.printSomeStuff();
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
