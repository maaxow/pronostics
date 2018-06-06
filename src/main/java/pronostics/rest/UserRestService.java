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

import pronostics.model.User;
import pronostics.repository.UserRepository;

@Component
@Path("/user")
public class UserRestService {

	@Inject
	private UserRepository userRepository;

	@GET
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public List<User> findAll() {
		if (userRepository != null) {
			List<User> users = userRepository.findAll();
//			return Response.ok(users).build();
			return users;
		} else {
//			return Response.status(Status.NOT_FOUND).build();
			return null;
		}
	}

	@GET
	@Path("/{id}")
	public Response findByIdToto(@PathParam("id") String id) {
		if (userRepository != null) {
			User game = userRepository.findById(Long.parseLong(id));
			System.out.println("[" + this.hashCode() + "] " + game.toString());
			return Response.ok(game).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
