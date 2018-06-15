package pronostics.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import pronostics.model.Pronostic;
import pronostics.repository.PronosticRepository;

@Component
@Path("/pronostic")
public class PronosticRestService {

	@Inject
	private PronosticRepository pronosticRepository;

	@GET
	public Response findAll() {
		if (pronosticRepository != null) {
			List<Pronostic> games = pronosticRepository.findAll();
			System.out.println(games.toString());
			return Response.ok(games.toString()).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response findByIdToto(@PathParam("id") String id) {
		if (pronosticRepository != null) {
			Pronostic game = pronosticRepository.findById(Long.parseLong(id));
			System.out.println("[" + this.hashCode() + "] " + game.toString());
			return Response.ok(game).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@GET
	@Path("/user/{id}")
	public Response findByUserId(@PathParam("id") Long id) {
		if (pronosticRepository != null) {
			List<Pronostic> pronoDone= pronosticRepository.findByUserId(id);
			return pronoDone!=null ? Response.ok(pronoDone).build() : null;
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@POST
	@Path("/new")
	public Response findByUserId(Pronostic prono) {
		System.out.println("prono : " + prono);
		if (pronosticRepository != null) {
			int nbPronoSaved = pronosticRepository.save(prono);
			return Response.ok(nbPronoSaved).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
