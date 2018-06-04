package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.User;
import pronostics.repository.sqlBuilder.UserSQLBuilder;

@Repository
public class UserRepository implements IRepository<User> {

	@Inject
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private static final UserSQLBuilder userBuilder = new UserSQLBuilder();
	private static final String findByIdQuery = userBuilder.buildFindByIdQuery();
	private static final String findAllQuery = userBuilder.buildFindAllQuery();
	private static final String saveQuery = userBuilder.buildSaveQuery();
	private static final String updateQuery = userBuilder.buildUpdateQuery();
	private static final String deleteQuery = userBuilder.buildDeleteQuery();

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User findById(long id) {
		List<User> users = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return toUser(resultSet);
		});

		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return toUser(resultSet);
		});
		return users;
	}

	@Override
	public int save(User t) {
		int nbRowAffected = jdbcTemplate.update(saveQuery,
				new Object[] { t.getUsername(), t.getPassword(), t.getFirstname(), t.getLastname() });
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

	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	private User toUser(ResultSet resultSet) {
		User user = new User();
		try {
			user.setId((Integer) resultSet.getInt("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFirstname(resultSet.getString("firstname"));
			user.setLastname(resultSet.getString("lastname"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
