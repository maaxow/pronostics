package pronostics.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pronostics.model.Group;
import pronostics.model.Team;
import pronostics.repository.TeamRepository;

@Service
public class TeamService {
	
	@Inject
	public TeamRepository teamRepository;

	public void printSomeStuff() {
		System.out.println("Des trucs");
	}
	
	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	public Team toTeam(ResultSet resultSet) {
		Team team = new Team();
		try {
			team.setId((Integer) resultSet.getInt("team_id"));
			team.setName(resultSet.getString("name"));
			String group = resultSet.getString("groupe"); 
			team.setGroup(Group.valueOf(group));
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
		return team;
	}
}
