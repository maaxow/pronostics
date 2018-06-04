package pronostics.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {

	@Id

	private int id;
	private Date date;
	private String tv;
	private String stadium;
	private Team team1;
	private Team team2;
	private int goalTeam1;
	private int goalTeam2;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the tv
	 */
	public String getTv() {
		return tv;
	}

	/**
	 * @param tv
	 *            the tv to set
	 */
	public void setTv(String tv) {
		this.tv = tv;
	}

	/**
	 * @return the stadium
	 */
	public String getStadium() {
		return stadium;
	}

	/**
	 * @param stadium
	 *            the stadium to set
	 */
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	/**
	 * @return the team1
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * @param team1
	 *            the team1 to set
	 */
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	/**
	 * @return the team2
	 */
	public Team getTeam2() {
		return team2;
	}

	/**
	 * @param team2
	 *            the team2 to set
	 */
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	/**
	 * @return the goalTeam1
	 */
	public int getGoalTeam1() {
		return goalTeam1;
	}

	/**
	 * @param goalTeam1
	 *            the goalTeam1 to set
	 */
	public void setGoalTeam1(int goalTeam1) {
		this.goalTeam1 = goalTeam1;
	}

	/**
	 * @return the goalTeam2
	 */
	public int getGoalTeam2() {
		return goalTeam2;
	}

	/**
	 * @param goalTeam2
	 *            the goalTeam2 to set
	 */
	public void setGoalTeam2(int goalTeam2) {
		this.goalTeam2 = goalTeam2;
	}

	@Override
	public String toString() {
		// Game (15) le 29/12/1992 sur TF1 a BOLAERT
		// 		FRANCE 0 - 0 BELGIQUE

		String teamName1 = getTeam1() != null ? getTeam1().getName() : "404";
		String teamName2 = getTeam2() != null ? getTeam2().getName() : "404";
		String str = "Game (" + getId() + ") le " + getDate().toString() + " sur " + getTv() + " a " + getStadium()
				+ "\n\t " + teamName1 + " " + getGoalTeam1() + " - " + getGoalTeam2() + " " + teamName2 + "\n";
		System.out.println(str);
		return str;

	}
}