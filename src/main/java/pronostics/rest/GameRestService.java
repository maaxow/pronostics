package pronostics.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pronostics.model.Game;
import pronostics.repository.GameRepository;

@Component
@Path("/")
public class GameRestService {

	@Autowired
	public GameRepository gameRepo;
	
	
	@GET
	@Path("/{id}")
	public Game findById(@PathParam("id") String id) {
		Game game = gameRepo.findById(Long.parseLong(id));
		System.out.println(game.toString());
		return game;
	}
	
}
