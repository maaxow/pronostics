package fr.maaxow.pronostics.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import fr.maaxow.pronostics.service.AuthService;

@Component
@Path("/update")
public class UpdateScoreRestService {

	@Inject
	public AuthService authService;

	@GET
	@Path("score/{idGame}/{nbBut1}-{nbBut2}")
	@Produces(value = { MediaType.APPLICATION_JSON_VALUE })
	public Response updateGameScores(@PathParam("idGame") int idGame, @PathParam("nbBut1") int nbBut1,
			@PathParam("nbBut2") int nbBut2) {
		//TODO finish
		System.out.println("id game : " + idGame);
		System.out.println("nb but 1 : " + nbBut1);
		System.out.println("nb but 2 : " + nbBut2);
		return Response.ok().build();
	}

}
