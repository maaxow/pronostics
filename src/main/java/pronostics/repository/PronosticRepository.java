package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.Game;
import pronostics.model.Pronostic;
import pronostics.model.User;
import pronostics.repository.sqlBuilder.PronosticSQLBuilder;

@Repository
public class PronosticRepository implements IRepository<Pronostic> {

	@Inject
	private DataSource dataSource;
	@Inject
	private GameRepository gameRepository;
	@Inject
	private UserRepository userRepository;
	private JdbcTemplate jdbcTemplate;

	private static final PronosticSQLBuilder pronosticBuilder = new PronosticSQLBuilder();
	private static final String findByIdQuery = pronosticBuilder.buildFindByIdQuery();
	private static final String findAllQuery = pronosticBuilder.buildFindAllQuery();
	private static final String saveQuery = pronosticBuilder.buildSaveQuery();
	private static final String updateQuery = pronosticBuilder.buildUpdateQuery();
	private static final String deleteQuery = pronosticBuilder.buildDeleteQuery();
	
	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	@Override
	public Pronostic findById(long id) {
		List<Pronostic> pronostics = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return toPronostic(resultSet);
		});

		if (pronostics.size() == 1) {
			return pronostics.get(0);
		}
		return null;
	}

	@Override
	public List<Pronostic> findAll() {
		List<Pronostic> pronostics = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return toPronostic(resultSet);
		});

		return pronostics;
	}

	@Override
	public int save(Pronostic t) {
		int nbRowAffected = jdbcTemplate.update(saveQuery,
				new Object[] { t.getGame().getId(), t.getUser().getId(), t.getGoalTeam1(), t.getGoalTeam2() });
		return nbRowAffected;
	}

	@Override
	public int delete(long id) {
		int nbRowAffected = jdbcTemplate.update(deleteQuery, new Object[] { id });
		return nbRowAffected;
	}

	@Override
	public int update(Pronostic t) {
		int nbRowAffected = jdbcTemplate.update(updateQuery, new Object[] { t.getGame().getId(), t.getUser().getId(),
				t.getGoalTeam1(), t.getGoalTeam2(), t.getId() });
		return nbRowAffected;

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
			Game game = gameRepository.findById((Integer) resultSet.getInt("game_id"));
			prono.setGame(game);
			User user = userRepository.findById((Integer) resultSet.getInt("user_id"));
			prono.setUser(user);
			prono.setGoalTeam1((Integer) resultSet.getInt("goal_team_1"));
			prono.setGoalTeam2((Integer) resultSet.getInt("goal_team_2"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prono;
	}

}
