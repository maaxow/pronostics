package pronostics.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pronostics.model.Team;
import pronostics.repository.sqlBuilder.TeamSQLBuilder;
import pronostics.service.TeamService;

@Repository
public class TeamRepository implements IRepository<Team> {

	@Inject
	private DataSource dataSource;
	@Inject
	private TeamService teamService;
	private JdbcTemplate jdbcTemplate;
	private static final TeamSQLBuilder teamBuilder = new TeamSQLBuilder();
	private static final String findByIdQuery = teamBuilder.buildFindByIdQuery();
	private static final String findAllQuery = teamBuilder.buildFindAllQuery();
	private static final String saveQuery = teamBuilder.buildSaveQuery();
	private static final String updateQuery = teamBuilder.buildUpdateQuery();
	private static final String deleteQuery = teamBuilder.buildDeleteQuery();
	private static final String findByGroupeQuery = "SELECT * FROM team WHERE groupe = ?";

	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Team findById(long id) {
		List<Team> teams = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return teamService.toTeam(resultSet);
		});

		if (teams.size() == 1) {
			if(teams.get(0) != null) {
				return teams.get(0);
			}
		}
		return null;
	}

	@Override
	public List<Team> findAll() {
		List<Team> teams = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return teamService.toTeam(resultSet);
		}); 
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
	
	public List<Team> findByGroup(String groupe) {
		List<Team> teams = jdbcTemplate.query(findByGroupeQuery, new Object[] { groupe }, (resultSet, i) -> {
			return teamService.toTeam(resultSet);
		});
		return teams;
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
