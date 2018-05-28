package pronostics.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import pronostics.model.Match;

@Repository
public class MatchRepository implements IRepository<Match> {

	@Override
	public void save(Match t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Match load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Match t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Match> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
