package pronostics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import pronostics.model.Game;
import pronostics.model.Pronostic;
import pronostics.model.User;


@Repository
public class PronosticRepository implements IRepository<Pronostic> {



	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private UserRepository userRepo;
	private JdbcTemplate jdbcTemplate;

//	private SimpleJdbcInsert jdbcInsert;

	@Override
	public int save(Pronostic t) {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public Pronostic findById(long id) {
		List<Pronostic> pronostics = jdbcTemplate.query("SELECT * from Pronostic where pronostic_id = ?",
				new Object[] { id }, (resultSet, i) -> {
					return toPronostic(resultSet);
				});

		if (pronostics.size() == 1) {
			return pronostics.get(0);
		}
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Pronostic t) {
		// TODO Auto-generated method stub
		return 0;

	}

	@Override
	public List<Pronostic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * mapping method, SQL -> Java
	 * 
	 * @param resultSet
	 * @return
	 */
	private Pronostic toPronostic(ResultSet resultSet) {
		Pronostic prono = new Pronostic();
		try {
			prono.setId((Integer) resultSet.getInt("pronostic_id"));
			Game game = gameRepo.findById((Integer) resultSet.getInt("game_id"));
			prono.setGame(game);
			User user = userRepo.findById((Integer) resultSet.getInt("user_id"));
			prono.setUser(user);
			prono.setGoalTeam1((Integer) resultSet.getInt("goal_team_1"));
			prono.setGoalTeam2((Integer) resultSet.getInt("goal_team_2"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
