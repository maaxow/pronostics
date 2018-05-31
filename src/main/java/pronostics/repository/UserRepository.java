package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.User;

@Repository
public class UserRepository implements IRepository<User> {

	private JdbcTemplate jdbcTemplate;
//	private SimpleJdbcInsert jdbcInsert;

	@Override
	public int save(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findById(long id) {
		List<User> users = jdbcTemplate.query("SELECT * from User where user_id = ?", new Object[] { id },
				(resultSet, i) -> {
					return toUser(resultSet);
				});

		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
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
			user.setId((Integer) resultSet.getInt("team_id"));
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
