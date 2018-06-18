package pronostics.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pronostics.model.Game;
import pronostics.model.Pronostic;
import pronostics.model.User;
import pronostics.repository.GameRepository;
import pronostics.repository.UserRepository;

@Service
public class PronosticService {
	
	@Inject
	public GameRepository gameRepository;
	
	@Inject
	public UserRepository userRepository;

	public void printSomeStuff() {
		System.out.println("Des trucs");
	}
	
	public int calculatePoint(Pronostic prono) {
		
		return 0;
	}
	
	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	public Pronostic toPronostic(ResultSet resultSet) {
		Pronostic prono = new Pronostic();
		try {
			prono.setId((Integer) resultSet.getInt("pronostic_id"));
			Game game = gameRepository.findById((Integer) resultSet.getInt("game_id"));
			prono.setGame(game);
			User user = userRepository.findById((Integer) resultSet.getInt("user_id"));
			prono.setUser(user);
			prono.setGoalTeam1((Integer) resultSet.getInt("goal_team_1"));
			prono.setGoalTeam2((Integer) resultSet.getInt("goal_team_2"));
			prono.setResultat(resultSet.getInt("resultat"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prono;
	}
}
