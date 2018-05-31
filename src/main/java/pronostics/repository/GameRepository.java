package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.Game;
import pronostics.model.Team;
import pronostics.repository.sqlBuilder.GameSQLBuilder;

@Repository
public class GameRepository implements IRepository<Game> {

	@Autowired
	private TeamRepository teamRepository;
	@Autowired 
	DataSource dataSource;
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
			return toGame(resultSet);
		});

		if (games.size() == 1) {
			return games.get(0);
		}
		return null;
	}

	@Override
	public List<Game> findAll() {
		return jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return toGame(resultSet);
		});
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

	private Game toGame(ResultSet resultSet) {
		Game game = new Game();
		try {
			game.setId((Integer) resultSet.getInt("game_id"));
			game.setDate((Timestamp) resultSet.getTimestamp("game_date"));
			game.setTv(resultSet.getString("tv"));
			game.setStadium(resultSet.getString("game_stadium"));
			
			Long teamId1 = (Long) resultSet.getLong("team_id_1");
			Team team1 = teamRepository.findById(teamId1);
			if(team1 != null) {
				game.setTeam1(team1);
			} else {
				System.err.println("Team " + teamId1 + " not found");
			}
			Long teamId2 = (Long) resultSet.getLong("team_id_2");
			Team team2 = teamRepository.findById(teamId2);
			if(team2 == null) {
				game.setTeam2(team2);
			} else {
				System.err.println("Team " + teamId2 + " not found");
			}
			game.setGoalTeam1((Integer) resultSet.getInt("goal_team_1"));
			game.setGoalTeam2((Integer) resultSet.getInt("goal_team_2"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return game;
	}

}
