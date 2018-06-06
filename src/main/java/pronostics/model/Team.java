package pronostics.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Team {

	private int id;
	private String name;
	private String group;
	private int nbGame;
	private int nbWin;
	private int nbDraw;
	private int nbLose;
	private int goalScored;
	private int goalTaken;
	private int point;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the nbGame
	 */
	public int getNbGame() {
		return nbGame;
	}

	/**
	 * @param nbGame
	 *            the nbGame to set
	 */
	public void setNbGame(int nbGame) {
		this.nbGame = nbGame;
	}

	/**
	 * @return the nbWin
	 */
	public int getNbWin() {
		return nbWin;
	}

	/**
	 * @param nbWin
	 *            the nbWin to set
	 */
	public void setNbWin(int nbWin) {
		this.nbWin = nbWin;
	}

	/**
	 * @return the nbDraw
	 */
	public int getNbDraw() {
		return nbDraw;
	}

	/**
	 * @param nbDraw
	 *            the nbDraw to set
	 */
	public void setNbDraw(int nbDraw) {
		this.nbDraw = nbDraw;
	}

	/**
	 * @return the nbLose
	 */
	public int getNbLose() {
		return nbLose;
	}

	/**
	 * @param nbLose
	 *            the nbLose to set
	 */
	public void setNbLose(int nbLose) {
		this.nbLose = nbLose;
	}

	/**
	 * @return the goalScored
	 */
	public int getGoalScored() {
		return goalScored;
	}

	/**
	 * @param goalScored
	 *            the goalScored to set
	 */
	public void setGoalScored(int goalScored) {
		this.goalScored = goalScored;
	}

	/**
	 * @return the goalTaken
	 */
	public int getGoalTaken() {
		return goalTaken;
	}

	/**
	 * @param goalTaken
	 *            the goalTaken to set
	 */
	public void setGoalTaken(int goalTaken) {
		this.goalTaken = goalTaken;
	}

	/**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
