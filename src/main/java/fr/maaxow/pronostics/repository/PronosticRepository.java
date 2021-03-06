package fr.maaxow.pronostics.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.maaxow.pronostics.model.Pronostic;
import fr.maaxow.pronostics.repository.sqlBuilder.PronosticSQLBuilder;
import fr.maaxow.pronostics.service.PronosticService;

@Repository
public class PronosticRepository implements IRepository<Pronostic> {

	@Inject
	private DataSource dataSource;
	@Inject
	private PronosticService pronosticService;
	private JdbcTemplate jdbcTemplate;

	private static final PronosticSQLBuilder pronosticBuilder = new PronosticSQLBuilder();
	private static final String findByIdQuery = pronosticBuilder.buildFindByIdQuery();
	private static final String findAllQuery = pronosticBuilder.buildFindAllQuery();
	private static final String saveQuery = pronosticBuilder.buildSaveQuery();
	private static final String updateQuery = pronosticBuilder.buildUpdateQuery();
	private static final String deleteQuery = pronosticBuilder.buildDeleteQuery();
	private static final String findByUserIdQuery = "SELECT * FROM pronostic WHERE user_id = ?;";
	private static final String findAllNotCalculatedQuery = "SELECT * FROM pronostic WHERE resultat = -99;";
	private static final String updateResultatQuery = "UPDATE pronostic SET resultat = ? WHERE pronostic_id = ?;";
	
	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	@Override
	public Pronostic findById(long id) {
		List<Pronostic> pronostics = jdbcTemplate.query(findByIdQuery, new Object[] { id }, (resultSet, i) -> {
			return pronosticService.toPronostic(resultSet);
		});

		if (pronostics.size() == 1) {
			return pronostics.get(0);
		}
		return null;
	}

	@Override
	public List<Pronostic> findAll() {
		List<Pronostic> pronostics = jdbcTemplate.query(findAllQuery, (resultSet, i) -> {
			return pronosticService.toPronostic(resultSet);
		});

		return pronostics;
	}

	public List<Pronostic> findByUserId(long id) {
		List<Pronostic> pronostics = jdbcTemplate.query(findByUserIdQuery, new Object[] { id }, (resultSet, i) -> {
			return pronosticService.toPronostic(resultSet);
		});
		
		return pronostics;
	}
	public List<Pronostic> findAllNotCalculated() {
		List<Pronostic> pronostics = jdbcTemplate.query(findAllNotCalculatedQuery, (resultSet, i) -> {
			return pronosticService.toPronostic(resultSet);
		});
		
		return pronostics;
	}
	
	@Override
	public int save(Pronostic t) {
		int nbRowAffected = jdbcTemplate.update(saveQuery,
				new Object[] { t.getGame().getId(), t.getUser().getId(), t.getGoalTeam1(), t.getGoalTeam2(), t.getResultat() });
		return nbRowAffected;
	}

	@Override
	public int delete(long id) {
		int nbRowAffected = jdbcTemplate.update(deleteQuery, new Object[] { id });
		return nbRowAffected;
	}

	@Override
	public int update(Pronostic t) {
		int nbRowAffected = jdbcTemplate.update(updateQuery, new Object[] { t.getGame().getId(), t.getUser().getId(),
				t.getGoalTeam1(), t.getGoalTeam2(), t.getId() });
		return nbRowAffected;
	}

	public int updateResultat(Pronostic t) {
		int nbRowAffected = jdbcTemplate.update(updateResultatQuery, new Object[] { t.getResultat(), t.getId() });
		return nbRowAffected;
	}

}
