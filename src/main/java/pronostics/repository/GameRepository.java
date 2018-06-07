package pronostics.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.Game;
import pronostics.repository.sqlBuilder.GameSQLBuilder;
import pronostics.service.GameService;

@Repository
public class GameRepository implements IRepository<Game> {

	@Inject
	private DataSource dataSource;
	@Inject
	public GameService gameService;
	private JdbcTemplate jdbcTemplate;

	private static final GameSQLBuilder gameBuilder = new GameSQLBuilder();
	private static final String findByIdQuery = gameBuilder.buildFindByIdQuery();
	private static final String findAllQuery = gameBuilder.buildFindAllQuery();
	private static final String saveQuery = gameBuilder.buildSaveQuery();
	private static final String updateQuery = gameBuilder.buildUpdateQuery();
	private static final String deleteQuery = gameBuilder.buildDeleteQuery();

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public Game findById(long id) {
		List<Game> games = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return gameService.toGame(resultSet);
		});
		if (games.size() == 1) {
			return games.get(0);
		}
		return null;
	}

	@Override
	public List<Game> findAll() {
		List<Game> games = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return gameService.toGame(resultSet);
		});
		return games;
	}

	@Override
	public int save(Game t) {
		return jdbcTemplate.update(saveQuery, new Object[] { t.getDate(), t.getId(), t.getStadium(),
				t.getTeam1().getId(), t.getTeam2().getId(), t.getGoalTeam1(), t.getGoalTeam2() });
	}

	@Override
	public int update(Game t) {
		return jdbcTemplate.update(updateQuery, new Object[] { t.getDate(), t.getTv(), t.getStadium(),
				t.getTeam1().getId(), t.getTeam2().getId(), t.getGoalTeam1(), t.getGoalTeam2(), t.getId() });
	}

	@Override
	public int delete(long id) {
		return jdbcTemplate.update(deleteQuery, new Object[] { id });

	}

}
