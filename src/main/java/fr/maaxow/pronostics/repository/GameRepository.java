package fr.maaxow.pronostics.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.maaxow.pronostics.model.Game;
import fr.maaxow.pronostics.repository.sqlBuilder.GameSQLBuilder;
import fr.maaxow.pronostics.service.GameService;

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
	/**
	 * Ended by "...(?" => finish the construction of the query
	 */
	private static final String findByExceptListIdQuery = "SELECT * FROM game WHERE game_id NOT IN (?";
	private static final String findAllByTeamIdQuery = "SELECT * FROM game WHERE (team_id_1 = ? OR team_id_2 = ?) AND game_date < NOW();";
	private static final String findAllWithoutScoreQuery = "SELECT * FROM game WHERE (goal_team_1 = -1 OR goal_team_2 = -1) AND game_date < NOW();";
	private static final String findAllFinalesQuery = "SELECT * FROM game g INNER JOIN team t ON g.team_id_1 = t.team_id WHERE t.groupe = \"W\" OR t.groupe = \"X\" OR t.groupe = \"Y\" OR t.groupe = \"Z\";";
	

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
	public List<Game> findByExceptListId(List<Long> ids) {
		String query = findByExceptListIdQuery;
		
		if(ids != null) {
			if(ids.size() > 0) {
				for(int i = 0; i < ids.size(); i++) {
					if(i == 0) {
						if(ids.size() > 1) {
							query = query.replace("?", "?,");
						}
					} else {
						if(i != (ids.size()-1)){	
							query = query.concat("?,");
						} else {
							query = query.concat("?");
						}
					}
				}
			}
		} else {
			return findAll();
		}
		query = query.concat(");");
		List<Game> games = jdbcTemplate.query(query, ids.toArray(), (resultSet, i) -> {
			return gameService.toGame(resultSet);
		});
		return games;
	}

	/**
	 * List all game with the team_id (1 or 2), and filtered by date before now();
	 * @param id
	 * @return all game with the team_id
	 */
	public List<Game> findAllByTeamId(int id) {
		List<Game> games = jdbcTemplate.query(findAllByTeamIdQuery, new Object[] {id, id},(resultSet, i) -> {
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

	/**
	 * return all game without a correct score
	 * @return
	 */
	public List<Game> findAllWithoutScore() {
		List<Game> games = jdbcTemplate.query(findAllWithoutScoreQuery,(resultSet, i) -> {
			return gameService.toGame(resultSet);
		});
		return games;
	}

	public List<Game> findFinales() {
		List<Game> games = jdbcTemplate.query(findAllFinalesQuery,(resultSet, i) -> {
			return gameService.toGame(resultSet);
		});
		return games;
	}


}
