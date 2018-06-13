package pronostics.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.User;
import pronostics.repository.sqlBuilder.UserSQLBuilder;
import pronostics.service.AuthService;
import pronostics.service.UserService;

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
	private static final String findByUsernameQuery = "SELECT * FROM Users WHERE username = ?;";

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
				new Object[] { t.getUsername(), authService.encryptPwd(t.getPassword()), t.getFirstname(), t.getLastname(), t.getRole().getName() });
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
				new Object[] { t.getUsername(), t.getPassword(), t.getFirstname(), t.getLastname(), t.getId() });
		return nbRowAffected;
	}

}
