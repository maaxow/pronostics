package pronostics.model;

public class Pronostic {

	private int id;
	private Game game;
	private User user;
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
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game
	 *            the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
}
