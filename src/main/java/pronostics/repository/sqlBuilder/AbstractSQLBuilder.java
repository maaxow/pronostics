package pronostics.repository.sqlBuilder;

import java.util.List;

public abstract class AbstractSQLBuilder<T> {

	protected String findByIdQuery;
	protected String findAllQuery;
	protected String saveQuery;
	protected String updateQuery;
	protected String deleteQuery;
	
	public String tableName;
	public String columnPKName;
	public List<String> columns;
	
	/**
	 * SELECT * FROM {table} WHERE {propID}=?
	 * @return String
	 */
	public String buildFindByIdQuery() {
		String query = "SELECT * FROM `";
		query += tableName + "` ";
		query += "WHERE " + columnPKName + " ";
		query += "= ?;";
		return query;
	}
	
	/**
	 * SELECT * FROM {table}
	 * @return String
	 */
	public String buildFindAllQuery() {
		String query = "SELECT * FROM `";
		query += tableName + "`;";
		return query;
	}
	
	/**
	 * INSERT INTO {table} ({prop1}, {prop2}, {prop3}, ...) VALUES (?, ?, ?, ...);
	 * <strong>do not add the id</strong>
	 * @return String
	 */
	public String buildSaveQuery() {
		String query = "INSERT INTO `"
				+ tableName + "`(";
		for(int i = 1; i < columns.size(); i++) { // start at 1 to skip the id (AUTO_INCREMENT)
			query += columns.get(i);
			if(i != columns.size()-1) {
				query += ", ";
			}
		}
		query += ") VALUES (";
		for(int i = 1; i < columns.size(); i++) { // start at 1 to skip the id (AUTO_INCREMENT)
			query += "?";
			if(i != columns.size()-1) {
				query += ", ";
			}
		}
		query += ");";
		return query;
	}
	
	/**
	 * UPDATE {table} SET {prop1}=?, {prop2}=?, ... WHERE {prop_ID}=?
	 * <strong> put the ID at the last</strong>
	 * @return String
	 */
	public String buildUpdateQuery() {
		String query = "UPDATE `"
				+ tableName + "` SET ";
		for(int i = 1; i < columns.size(); i++) { // start at 1 to skip the id (AUTO_INCREMENT)
			query += columns.get(i) + "=?";
			if(i != columns.size()-1) {
				query += ", ";
			}
		}
		query += " WHERE ";
		query += columnPKName + "=?";
		return query;
	}
	
	/**
	 * DELETE FROM {table} WHERE {propID}=?
	 * @return
	 */
	public String buildDeleteQuery() {
		String query = "DELETE FROM `"
				+ tableName 
				+ "` WHERE "
				+ columnPKName + "=?;";
//		System.out.println("buildDeleteQuery() : \n" + query);
		return query;
	}
}
