package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.Team;
import pronostics.repository.sqlBuilder.TeamSQLBuilder;

@Repository
public class TeamRepository implements IRepository<Team> {

	@Inject
	private DataSource dataSource;
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
	public Team findById(long id) {
		List<Team> teams = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return toTeam(resultSet);
		});

//		closeConnection();
		if (teams.size() == 1) {
			return teams.get(0);
		}
		return null;
	}

	@Override
	public List<Team> findAll() {
		List<Team> teams = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return toTeam(resultSet);
		}); 
//		closeConnection();
		return teams;
		
	}
	
	@Override
	public int save(Team t) {
		int nbRowAffected = jdbcTemplate.update(saveQuery, new Object[] {
				t.getName(),
				t.getGroup(),
				t.getNbGame(),
				t.getNbWin(),
				t.getNbDraw(),
				t.getNbLose(),
				t.getGoalScored(),
				t.getGoalTaken(),
				t.getPoint(),
		}); 
		return nbRowAffected;
		
	}
	
	@Override
	public int delete(long id) {
		int nbRowAffected = jdbcTemplate.update(deleteQuery, new Object[] {id}); 
		return nbRowAffected;
	}

	@Override
	public int update(Team t) {
		int nbRowAffected = jdbcTemplate.update(updateQuery, new Object[] {
				t.getName(),
				t.getGroup(),
				t.getNbGame(),
				t.getNbWin(),
				t.getNbDraw(),
				t.getNbLose(),
				t.getGoalScored(),
				t.getGoalTaken(),
				t.getPoint(),
				t.getId()
		}); 
		return nbRowAffected;
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
	
	// private void closeConnection() {
	// try {
	// dataSource.getConnection().close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
