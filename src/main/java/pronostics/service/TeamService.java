package pronostics.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import pronostics.model.Gagnant;
import pronostics.model.Game;
import pronostics.model.Group;
import pronostics.model.Resultat;
import pronostics.model.Team;
import pronostics.repository.GameRepository;
import pronostics.repository.TeamRepository;

@Service
public class TeamService {
	
	@Inject
	public TeamRepository teamRepository;
	@Inject
	public GameRepository gameRepository;

	public void printSomeStuff() {
		System.out.println("Des trucs");
	}
	
	public void updatePoints(List<Team> teams) {
		for(Team team : teams) {
			updatePoints(team);
		}
	}
	
	/**
	 * Update all score and points, and Save in bdd
	 * @param team
	 */
	public void updatePoints(Team team) {
		
		List<Game> games = gameRepository.findAllByTeamId(team.getId());
		int nbWin = 0;
		int nbDraw = 0;
		int nbLose = 0;
		int nbGoalScored = 0;
		int nbGoalTaken = 0;
		int nbPoint = 0;
		int position = 0;
		
		if(games != null) {
			team.setNbGame(games.size());
			
			for(Game game : games) {
				position = game.getPositionNumber(team.getId());
				
				Resultat result = new Resultat(game);
				if(position == 1) {
					if(result.getWinner().equals(Gagnant.UN)) { // WIN
						nbWin ++;
						nbPoint += 3;
					}
					else if(result.getLoser().equals(Gagnant.UN)) { // LOSE
						nbLose ++;
					}
					else { // DRAW
						nbDraw ++;
						nbPoint ++;
					}
					nbGoalScored += result.getNbBut1();
					nbGoalScored += result.getNbBut2();
				}
				else if(position == 2) {
					if(result.getWinner().equals(Gagnant.DEUX)) { // WIN
						nbWin ++;
						nbPoint += 3;
					}
					else if(result.getLoser().equals(Gagnant.DEUX)) { // LOSE
						nbLose ++;
					}
					else { // DRAW
						nbDraw ++;
						nbPoint ++;
					}
					nbGoalScored += result.getNbBut2();
					nbGoalScored += result.getNbBut1();
					
				}
			}
			team.setNbWin(nbWin);
			team.setNbDraw(nbDraw);
			team.setNbLose(nbLose);
			team.setGoalScored(nbGoalScored);
			team.setGoalTaken(nbGoalTaken);
			team.setPoint(nbPoint);
			System.out.println("Groupe of the team : "+ team + " groupe : " + team.getGroup());
			teamRepository.update(team);
		}
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
