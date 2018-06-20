package pronostics.repository.sqlBuilder;

import java.util.ArrayList;

import pronostics.model.Game;

public class PronosticSQLBuilder extends AbstractSQLBuilder<Game> {

	public PronosticSQLBuilder() {
		this.tableName = "pronostic";
		this.columnPKName = "pronostic_id";
		this.columns = new ArrayList<String>();
		this.columns.add("pronostic_id");
		this.columns.add("game_id");
		this.columns.add("user_id");
		this.columns.add("goal_team_1");
		this.columns.add("goal_team_2");
	}
}
