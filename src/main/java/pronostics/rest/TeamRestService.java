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

import pronostics.model.Team;
import pronostics.repository.TeamRepository;
import pronostics.service.TeamService;

@Component
@Path("/team")
public class TeamRestService {

	@Inject
	private TeamRepository teamRepository;
	@Inject
	private TeamService teamService;

	@GET
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findAll() {
		if (teamRepository != null) {
			List<Team> teams = teamRepository.findAll();
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findByIdToto(@PathParam("id") String id) {
		if (teamRepository != null) {
			Team team = teamRepository.findById(Long.parseLong(id));
			return Response.ok(team).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("/groupe/{groupe}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findByGroupe(@PathParam("groupe") String groupe) {
		if (teamRepository != null) {
			List<Team> game = teamRepository.findByGroup(groupe);
			return Response.ok(game).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@GET
	@Path("/update/score")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response updatePoints() {
		System.out.println("Updating points of teams ...");
		if (teamRepository != null) {
			List<Team> teams = teamRepository.findAll();
			teamService.updatePoints(teams);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	

}
