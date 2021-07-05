package fr.maaxow.pronostics.repository.sqlBuilder;

import java.util.ArrayList;

import fr.maaxow.pronostics.model.Team;

public class TeamSQLBuilder extends AbstractSQLBuilder<Team> {

	public TeamSQLBuilder() {
		this.tableName = "team";
		this.columnPKName = "team_id";
		this.columns = new ArrayList<String>();
		this.columns.add("team_id");
		this.columns.add("name");
		this.columns.add("groupe");
		this.columns.add("nb_game");
		this.columns.add("nb_win");
		this.columns.add("nb_draw");
		this.columns.add("nb_lose");
		this.columns.add("goal_scored");
		this.columns.add("goal_taken");
		this.columns.add("point");
	}
}
