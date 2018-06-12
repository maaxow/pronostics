package pronostics.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import pronostics.model.Role;
import pronostics.model.User;

@Service
public class UserService {
	
	
	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	public User toUser(ResultSet resultSet) {
		User user = new User();
		try {
			user.setId((Integer) resultSet.getInt("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFirstname(resultSet.getString("firstname"));
			user.setLastname(resultSet.getString("lastname"));
			Role role = Role.valueOf(resultSet.getString("user_role"));
			user.setRole(role);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
