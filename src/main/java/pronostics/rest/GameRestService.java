package pronostics.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import pronostics.model.Game;
import pronostics.repository.GameRepository;

@Component
@Path("/game")
public class GameRestService {

	@Inject
	private GameRepository gameRepository;

	@GET
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findAll() {
		if (gameRepository != null) {
			List<Game> games = gameRepository.findAll();
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findById(@PathParam("id") String id) {
		if (gameRepository != null) {
			Game game = gameRepository.findById(Long.parseLong(id));
			return Response.ok(game).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
