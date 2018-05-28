package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import pronostics.model.Team;

public class TeamRepository implements IRepository<Team> {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;

	
	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("TEAM")
				.usingGeneratedKeyColumns("team_id");
	}
	
	@Override
	public void save(Team t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Team findById(long id) {
		List<Team> games = jdbcTemplate.query("SELECT * from Game where game_id = ?", new Object[] { id },
				(resultSet, i) -> {
					return toTeam(resultSet);
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
	public void update(Team t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Team> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	private Team toTeam(ResultSet resultSet) {
		Team team = new Team();
		try {
			team.setId((Integer) resultSet.getInt("team_id"));
			team.setName(resultSet.getString("name"));
			team.setGroup(resultSet.getString("groupe"));
			team.setNbGame((Integer) resultSet.getInt("nb_game"));
			team.setNbWin((Integer) resultSet.getInt("nb_win"));
			team.setNbDraw((Integer) resultSet.getInt("nb_draw"));
			team.setNbLose((Integer) resultSet.getInt("nb_lose"));
			team.setGoalScored((Integer) resultSet.getInt("goal_scored"));
			team.setGoalTaken((Integer) resultSet.getInt("goal_taken"));
			team.setPoint((Integer) resultSet.getInt("point"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
