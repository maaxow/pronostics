package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import pronostics.model.Game;
import pronostics.model.Team;

@Repository
public class GameRepository implements IRepository<Game> {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private TeamRepository teamRepository;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("GAME").usingGeneratedKeyColumns("game_id");
	}

	@Override
	public void save(Game t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Game findById(long id) {
		List<Game> games = jdbcTemplate.query("SELECT * from Game where game_id = ?", new Object[] { id },
				(resultSet, i) -> {
					return toGame(resultSet);
				});

		if (games.size() == 1) {
			return games.get(0);
		}
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Game> findAll() {
		return jdbcTemplate.query("SELECT * FROM Game", (resultSet, i) -> {
			return toGame(resultSet);
		});
	}

	private Game toGame(ResultSet resultSet) {
		Game game = new Game();
		try {
			game.setId((Integer) resultSet.getInt("game_id"));
			game.setDate((Timestamp) resultSet.getTimestamp("game_date"));
			game.setTv(resultSet.getString("tv"));
			game.setStadium(resultSet.getString("game_stadium"));
			Team team1 = teamRepository.findById((Integer) resultSet.getInt("team_id_1"));
			game.setTeam1(team1);
			Team team2 = teamRepository.findById((Integer) resultSet.getInt("team_id_2"));
			game.setTeam2(team2);
			game.setGoalTeam1((Integer) resultSet.getInt("goal_team_1"));
			game.setGoalTeam2((Integer) resultSet.getInt("goal_team_2"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
