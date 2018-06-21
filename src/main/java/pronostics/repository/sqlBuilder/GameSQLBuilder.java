package pronostics.repository.sqlBuilder;

import java.util.ArrayList;

import pronostics.model.Game;

public class GameSQLBuilder extends AbstractSQLBuilder<Game> {

	public GameSQLBuilder() {
		this.tableName = "game";
		this.columnPKName = "game_id";
		this.columns = new ArrayList<String>();
		this.columns.add("game_id");
		this.columns.add("game_date");
		this.columns.add("tv");
		this.columns.add("game_stadium");
		this.columns.add("team_id_1");
		this.columns.add("team_id_2");
		this.columns.add("goal_team_1");
		this.columns.add("goal_team_2");
	}
}
