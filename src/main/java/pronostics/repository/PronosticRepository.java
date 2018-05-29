package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pronostics.model.Game;
import pronostics.model.Pronostic;
import pronostics.model.User;

public class PronosticRepository implements IRepository<Pronostic> {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private UserRepository userRepo;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("PRONOSTIC")
				.usingGeneratedKeyColumns("pronostic_id");
	}

	@Override
	public void save(Pronostic t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pronostic findById(long id) {
		List<Pronostic> pronostics = jdbcTemplate.query("SELECT * from Pronostic where pronostic_id = ?",
				new Object[] { id }, (resultSet, i) -> {
					return toPronostic(resultSet);
				});

		if (pronostics.size() == 1) {
			return pronostics.get(0);
		}
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Pronostic t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pronostic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	private Pronostic toPronostic(ResultSet resultSet) {
		Pronostic prono = new Pronostic();
		try {
			prono.setId((Integer) resultSet.getInt("pronostic_id"));
			Game game = gameRepo.findById((Integer) resultSet.getInt("game_id"));
			prono.setGame(game);
			User user = userRepo.findById((Integer) resultSet.getInt("user_id"));
			prono.setUser(user);
			prono.setGoalTeam1((Integer) resultSet.getInt("goal_team_1"));
			prono.setGoalTeam2((Integer) resultSet.getInt("goal_team_2"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
