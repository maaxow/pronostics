package pronostics.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import pronostics.model.Team;
import pronostics.repository.TeamRepository;

@Component
@Path("/team")
public class TeamRestService {

	@Inject
	private TeamRepository teamRepository;

	@GET
	public Response findAll() {
		if (teamRepository != null) {
			List<Team> games = teamRepository.findAll();
			System.out.println(games.toString());
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response findByIdToto(@PathParam("id") String id) {
		if (teamRepository != null) {
			Team team = teamRepository.findById(Long.parseLong(id));
			System.out.println("[" + this.hashCode() + "] " + team.toString());
			return Response.ok(team).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
