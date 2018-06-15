package pronostics.rest;

import java.util.HashMap;
import java.util.Map;

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

import pronostics.model.User;
import pronostics.repository.UserRepository;
import pronostics.service.AuthService;

@Component
@Path("/auth")
public class AuthRestService {
	
	@Inject
	public AuthService authService;

//	@GET
//	@Path("/{id}")
//	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
//	public Response findById(@PathParam("id") Long id) {
//		if (userRepository != null) {
//			User user = userRepository.findById(id);
//			return Response.ok(user).build();
//		} else {
//			return Response.status(Status.NOT_FOUND).build();
//		}
//	}
	
	
	@POST
	@Path("authenticate")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response checkAuth(Map<String, String> logger) {
		String login = logger.get("login");
		String password = logger.get("password");
//		System.out.println("trying to connect with " + login + " " + password);
		User userLogged = authService.checkAuth(login, password);
//		Map<String, String> response = new HashMap<String, String>(1);
//		response.put("token", token);
//		System.out.println("maaxow encrypted " + authService.pwdEncryptor.encryptPassword("maaxow"));
//		System.out.println("dodie encrypted " + authService.pwdEncryptor.encryptPassword("dodie"));
//		System.out.println("check auth " + authService.pwdEncryptor.checkPassword(password, "EhAWm61+TG8nN6K1oadAE+bEl6uVUw4s9yC6QnLCmcoCOMrCZHF1RQgogLgKIU65"));
//		System.out.println("user logged : " + userLogged);
		if(userLogged != null) {
			return Response.ok(userLogged).build();
		} 
		else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	
	
	@POST
	@Path("token/valid")
	@Produces(value = {MediaType.APPLICATION_JSON_VALUE})
	public Response isValidToken(Map<String, String> logger) {
		String login = logger.get("login");
		String token = logger.get("token");
		boolean isValid = authService.isValidToken(login, token);
		if(isValid) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	
}
