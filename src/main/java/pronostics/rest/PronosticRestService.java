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
import pronostics.service.PronosticService;

@Component
@Path("/pronostic")
public class PronosticRestService {

	@Inject
	private PronosticRepository pronosticRepository;
	@Inject
	private PronosticService pronosticService;

	@GET
	public Response findAll() {
		if (pronosticRepository != null) {
			List<Pronostic> games = pronosticRepository.findAll();
			return Response.ok(games).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	public Response findByIdToto(@PathParam("id") String id) {
		if (pronosticRepository != null) {
			Pronostic game = pronosticRepository.findById(Long.parseLong(id));
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
	@GET
	@Path("/update/points")
	public Response updatePoints() {
		if (pronosticRepository != null) {
			List<Pronostic> pronos = pronosticRepository.findAllNotCalculated();
			for(Pronostic prono : pronos) {
				if(prono.getGame().getGoalTeam1() != 1 && prono.getGame().getGoalTeam2() != 1) {
					int points = pronosticService.calculatePoint(prono);
					prono.setResultat(points);
					pronosticRepository.updateResultat(prono);
				}
			}
			return Response.ok(pronos).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@POST
	@Path("/update/points")
	public Response updatePronoPoints(Pronostic prono) {
		if (pronosticRepository != null) {
			int pronos = pronosticRepository.updateResultat(prono);
			return Response.ok(pronos).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	@GET
	@Path("/calculate/points/{pronoId}")
	public Response updatePoints(@PathParam("pronoId") String pronoId) {
		if (pronosticRepository != null) {
			Pronostic prono = pronosticRepository.findById(Long.parseLong(pronoId));
				if(prono.getGame().getGoalTeam1() != 1 && prono.getGame().getGoalTeam2() != 1) {
					int points = pronosticService.calculatePoint(prono);
					return Response.ok(points).build();
				} else {
					return Response.ok(-99).build();
			}
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
