package fr.maaxow.pronostics.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import fr.maaxow.pronostics.model.Game;
import fr.maaxow.pronostics.model.Team;
import fr.maaxow.pronostics.repository.GameRepository;
import fr.maaxow.pronostics.repository.TeamRepository;

@Component
@Path("/game")
public class GameRestService {

	@Inject
	private GameRepository gameRepository;
	@Inject
	private TeamRepository teamRepository;

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
	@POST
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response update(Game game) {
		if (gameRepository != null) {
			gameRepository.update(game);
			return Response.ok().build();
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
	@POST
	@Path("/except")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findByExceptListId(List<Long> ids) {
		if (gameRepository != null) {
			List<Game> game = gameRepository.findByExceptListId(ids);
			if(game != null) {
				return Response.ok(game).build();
			}
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	@SuppressWarnings("deprecation")
	@GET
	@Path("/filter/date")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findFilterByDate() {
		if (gameRepository != null) {
			List<Game> games = gameRepository.findAll();
			Map<Long, List<Game>> result = new TreeMap<Long, List<Game>>(
				new Comparator<Long>() {
						@Override
						public int compare(Long l1, Long l2) {
							return l1.compareTo(l2);
						}
			});
			ArrayList<Game> newList = null;
			Date dateWithoutHours = null;
			for(Game game : games) {
				dateWithoutHours = new Date(game.getDate().getTime());
				dateWithoutHours.setHours(0);
				dateWithoutHours.setMinutes(0);
				dateWithoutHours.setSeconds(0);
				if(!result.containsKey(dateWithoutHours.getTime())) {
					newList = new ArrayList<Game>();
					newList.add(game);
					result.put(dateWithoutHours.getTime(), newList);
				} else {
					result.get(dateWithoutHours.getTime()).add(game);
				}
			}
			
			return Response.ok(result).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@GET
	@Path("/not/scored")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response getNotScored() {
		if (gameRepository != null) {
			List<Game> games = gameRepository.findAllWithoutScore();
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("/finales")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findFinales() {
		if (gameRepository != null) {
			List<Game> games = gameRepository.findFinales();
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
}
