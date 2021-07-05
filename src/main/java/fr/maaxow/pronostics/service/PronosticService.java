package fr.maaxow.pronostics.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import fr.maaxow.pronostics.model.Game;
import fr.maaxow.pronostics.model.Pronostic;
import fr.maaxow.pronostics.model.Resultat;
import fr.maaxow.pronostics.model.User;
import fr.maaxow.pronostics.repository.GameRepository;
import fr.maaxow.pronostics.repository.UserRepository;

@Service
public class PronosticService {
	
	@Inject
	public GameRepository gameRepository;
	
	@Inject
	public UserRepository userRepository;

	public int calculatePoint(Pronostic prono) {
		
		Resultat pronoRes = new Resultat(prono);
		Resultat gameRes = new Resultat(prono.getGame());
		
		return Resultat.calculateNbPoint(pronoRes, gameRes);
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
