package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import pronostics.model.Team;
import pronostics.repository.sqlBuilder.TeamSQLBuilder;

@Repository
public class TeamRepository implements IRepository<Team> {

	@Autowired
	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private static final TeamSQLBuilder teamBuilder = new TeamSQLBuilder();
	private static final String findByIdQuery = teamBuilder.buildFindByIdQuery();
	private static final String findAllQuery = teamBuilder.buildFindAllQuery();
	private static final String saveQuery = teamBuilder.buildSaveQuery();
	private static final String updateQuery = teamBuilder.buildUpdateQuery();
	private static final String deleteQuery = teamBuilder.buildDeleteQuery();


	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public int save(Team t) {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public Team findById(long id) {

		List<Team> games = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return toTeam(resultSet);
		});


		if (games.size() == 1) {
			return games.get(0);
		}
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Team t) {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public List<Team> findAll() {
		return jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return toTeam(resultSet);
		});

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
