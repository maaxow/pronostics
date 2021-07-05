package fr.maaxow.pronostics.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import fr.maaxow.pronostics.model.Pronostic;
import fr.maaxow.pronostics.model.Role;
import fr.maaxow.pronostics.model.User;
import fr.maaxow.pronostics.repository.PronosticRepository;
import fr.maaxow.pronostics.repository.UserRepository;
import fr.maaxow.pronostics.service.AuthService;

@Component
@Path("/user")
public class UserRestService {

	@Inject
	private UserRepository userRepository;
	@Inject
	private PronosticRepository pronosticRepository;
	@Inject
	private AuthService authService;

	@GET
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findAll() {
		if (userRepository != null) {
			List<User> users = userRepository.findAll();
			return Response.ok(users).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response findById(@PathParam("id") Long id) {
		if (userRepository != null) {
			User user = userRepository.findById(id);
			return Response.ok(user).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response addUser(User user) {
		System.out.println("new user :" + user.toString());
		user.setRole(Role.USER);
		int nbRows = userRepository.save(user);
		return Response.ok(nbRows).build();
	}
	
	@POST
	@Path("/{userId}/{firstname}/{lastname}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response updateUser(
			@PathParam("userId") String userId, 
			@PathParam("firstname") String firstname, 
			@PathParam("lastname") String lastname) {
		User user = userRepository.findById(Long.parseLong(userId));
		user.setFirstname(firstname);
		user.setLastname(lastname);
		int nbRows = userRepository.updateWithoutPassword(user);
		return Response.ok(nbRows).build();
	}
	
	@POST
	@Path("/{userId}/{password}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response updateUserPassword(
			@PathParam("userId") String userId, 
			@PathParam("password") String password) {
		User user = userRepository.findById(Long.parseLong(userId));
		user.setPassword(password);
		int nbRows = userRepository.updatePassword(user);
		return Response.ok(nbRows).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response deleteUser(@QueryParam("id") String id) {
		int nbRows = userRepository.delete(Long.parseLong(id));
		return Response.ok(nbRows).build();
	}
	
	@GET
	@Path("/classement")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response updateClassement() {
		List<User> users = userRepository.findAll();
		int scoreTotal;
		for(User user : users) {
			scoreTotal = 0;
			List<Pronostic> pronos = pronosticRepository.findByUserId(user.getId());
			for(Pronostic prono : pronos) {
				if(prono.getResultat() >= -1) {
					scoreTotal += prono.getResultat();
				}
			}
			user.setPoint(scoreTotal);
			userRepository.updatePoint(user);
		}
		// get all pronoo by user, and get all point
		return Response.ok().build();
	}
}
