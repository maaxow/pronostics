package pronostics.repository.sqlBuilder;

import java.util.ArrayList;

import pronostics.model.Game;

public class UserSQLBuilder extends AbstractSQLBuilder<Game> {

	public UserSQLBuilder() {
		this.tableName = "Users";
		this.columnPKName = "user_id";
		this.columns = new ArrayList<String>();
		this.columns.add("user_id");
		this.columns.add("username");
		this.columns.add("password");
		this.columns.add("firstname");
		this.columns.add("lastname");
	}
}