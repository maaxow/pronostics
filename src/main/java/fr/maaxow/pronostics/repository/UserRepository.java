package fr.maaxow.pronostics.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.maaxow.pronostics.model.User;
import fr.maaxow.pronostics.repository.sqlBuilder.UserSQLBuilder;
import fr.maaxow.pronostics.service.AuthService;
import fr.maaxow.pronostics.service.UserService;

@Repository
public class UserRepository implements IRepository<User> {

	@Inject
	private DataSource dataSource;
	@Inject
	public AuthService authService;
	@Inject
	private UserService userService;
	private JdbcTemplate jdbcTemplate;
	private static final UserSQLBuilder userBuilder = new UserSQLBuilder();
	private static final String findByIdQuery = userBuilder.buildFindByIdQuery();
	private static final String findAllQuery = userBuilder.buildFindAllQuery();
	private static final String saveQuery = userBuilder.buildSaveQuery();
	private static final String updateQuery = userBuilder.buildUpdateQuery();
	private static final String deleteQuery = userBuilder.buildDeleteQuery();
	private static final String findByUsernameQuery = "SELECT * FROM users WHERE username = ?;";
	private static final String updateWithourPasswordQuery = "UPDATE users SET firstname=?, lastname=? WHERE user_id=?;";
	private static final String updatePasswordQuery = "UPDATE users SET password=? WHERE user_id=?;";
	private static final String updatePointQuery = "UPDATE users SET user_point = ? WHERE user_id = ?;";

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User findById(long id) {
		List<User> users = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return userService.toUser(resultSet);
		});

		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return userService.toUser(resultSet);
		});
		return users;
	}

	public User findByUserName(String username) {
		List<User> users = jdbcTemplate.query(findByUsernameQuery, new Object[] { username }, (resultSet, i) -> {
			return userService.toUser(resultSet);
		});

		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public int save(User t) {
		int nbRowAffected = jdbcTemplate.update(saveQuery,
				new Object[] { t.getUsername(), authService.encryptPwd(t.getPassword()), t.getFirstname(), t.getLastname(), t.getRole().getName(), t.getPoint() });
		return nbRowAffected;
	}

	@Override
	public int delete(long id) {
		int nbRowAffected = jdbcTemplate.update(deleteQuery, new Object[] { id });
		return nbRowAffected;
	}

	@Override
	public int update(User t) {
		int nbRowAffected = jdbcTemplate.update(updateQuery,
				new Object[] { t.getUsername(), authService.encryptPwd(t.getPassword()), t.getFirstname(), t.getLastname(), t.getId() });
		return nbRowAffected;
	}

	public int updateWithoutPassword(User t) {
		int nbRowAffected = jdbcTemplate.update(updateWithourPasswordQuery,
				new Object[] { t.getFirstname(), t.getLastname(), t.getId() });
		return nbRowAffected;
	}

	public int updatePassword(User user) {
		int nbRowAffected = jdbcTemplate.update(updatePasswordQuery,
				new Object[] { authService.encryptPwd(user.getPassword()), user.getId() });
		return nbRowAffected;
	}
	
	public int updatePoint(User user) {
		int nbRowAffected = jdbcTemplate.update(updatePointQuery,
				new Object[] { user.getPoint(), user.getId() });
		return nbRowAffected;
	}

}
