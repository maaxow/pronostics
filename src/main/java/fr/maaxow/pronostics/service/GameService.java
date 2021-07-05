package fr.maaxow.pronostics.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import fr.maaxow.pronostics.model.Game;
import fr.maaxow.pronostics.model.Team;
import fr.maaxow.pronostics.repository.TeamRepository;

@Service
public class GameService {
	
	@Inject
	public TeamRepository teamRepository;

	public Game toGame(ResultSet resultSet) {
		Game game = new Game();
		try {
			game.setId((Integer) resultSet.getInt("game_id"));
			game.setDate((Timestamp) resultSet.getTimestamp("game_date"));
			game.setTv(resultSet.getString("tv"));
			game.setStadium(resultSet.getString("game_stadium"));

			Long teamId1 = (Long) resultSet.getLong("team_id_1");
			Team team1 = teamRepository.findById(teamId1);
			if (team1 != null) {
				game.setTeam1(team1);
			} else {
				System.err.println("Team " + teamId1 + " not found");
			}
			Long teamId2 = (Long) resultSet.getLong("team_id_2");
			Team team2 = teamRepository.findById(teamId2);
			if (team2 != null) {
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
