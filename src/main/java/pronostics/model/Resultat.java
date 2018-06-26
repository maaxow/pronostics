package pronostics.model;

public class Resultat {

	int nbBut1;
	int nbBut2;
	Gagnant winner;
	
	public Resultat(Game game){
		nbBut1 = game.getGoalTeam1();
		nbBut2 = game.getGoalTeam2();
		winner = Gagnant.getWinner(nbBut1, nbBut2);
	}
	
	public Resultat(Pronostic prono){
		nbBut1 = prono.getGoalTeam1();
		nbBut2 = prono.getGoalTeam2();
		winner = Gagnant.getWinner(nbBut1, nbBut2);
	}
	
	public int getNbButWinner(){
		return winner == Gagnant.UN ? getNbBut1() : getNbBut2();
//		if(winner == Gagnant.UN){
//			return getNbBut1();
//		}
//		// si DEUX ou DRAW
//		else {
//			return getNbBut2();
//		}
	}
	
	public int getNbButLoser(){
		return winner == Gagnant.UN ? getNbBut2() : getNbBut1();
//		if(winner == Gagnant.UN){
//			return getNbBut2();
//		}
//		// si DEUX ou DRAW
//		else {
//			return getNbBut1();
//		}
	}
	
	/**
	 * Score exact : 10 points
	 * Score gagnant OK : 7 points
	 * Bon pronostique : 5 points
	 * Pas de prono : 0
	 * Pronostique faux : -1
	 * 
	 * @param prono
	 * @param game
	 * @return
	 */
	public static int calculateNbPoint(Resultat prono, Resultat game){
		if(prono.winner == game.winner){ // Bon prono
			if(prono.getNbButWinner() == game.getNbButWinner()){ // bon nb but gagnant
				if(prono.getNbButLoser() == game.getNbButLoser()){ // bon nb but perdant
					return 10;
				}
				else { // pas bon nb but perdant
					return 7;
				}
			} else { // pas bon nb but gagnant
				return 5;
			}
		}
		else { // pas bon prono
			return -1;
		}
	}
	
	public int getNbBut1() {
		return this.nbBut1;
	}
	public int getNbBut2() {
		return this.nbBut2;
	}
	public Gagnant getWinner() {
		return winner;
	}
	public Gagnant getLoser() {
		return Gagnant.getLoser(getNbBut1(), getNbBut2());
	}
}
