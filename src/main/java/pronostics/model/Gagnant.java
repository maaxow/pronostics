package pronostics.model;

public enum Gagnant {

	UN, DEUX, DRAW;
	
	
	public static Gagnant getWinner(int goalTeam1, int goalTeam2) {
		if(goalTeam1 > goalTeam2){
			return Gagnant.UN;
		}
		else if(goalTeam1 < goalTeam2){
			return Gagnant.DEUX;
		}
		else { 
			return Gagnant.DRAW; 
		}
	}
}
